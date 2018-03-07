package video.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 *
 * @author diana.barrios 
 * @author NissimBetesh
 */
public class Game implements Runnable {

    private BufferStrategy bs;              // to have several buffers when displaying
    private Graphics g;                     // to paint objects;
    private Display display;                // to display in the game
    String title;                           // title of the window
    private int width;                      // width of the window
    private int height;                     // height of the window
    private Thread thread;                  // thread to create the game
    private boolean running;                // to set the game
    private Player player;                  // to use a player
    private ArrayList<Bullet> bullets;      // to store the shots fired by the player
    private ArrayList<Bullet> enemyShot;    // enemy shots taken
    private ArrayList<Ghosts> ghosts;       // to store ghosts
    private GhostCont ghostsCont;           // matrix storing ghosts
    private KeyManager keyManager;          // to manage the keyboard
    private boolean gameOver;               // to end the game
    private int lives;                      // number of lives for the player
    private int score;                      // player score
    private int moveDist;                   // ghosts horizontal distance
    private int ghostCol;                   // col of the ghost array
    private int ghostRow;                   //  row of the ghost array
    private SoundClip pacmanLoosesLive;     //to use sound of loosing lives
    private SoundClip pacmanKillsGhost;     //to use sound of ghost killed
    private SoundClip pacmanRestart;     //to use sound of restart game
    private SoundClip pacmanShoots;     //to use sound of pacman shooting


    /**
     * to create title, width and height and set the game
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        gameOver = false;
        lives = 3;
        score = 0;
        // distance -> this.width() - (7*ghostWidth + 6*spacing + 2*margin)
        moveDist = width - (12*50 + 11*10 + 2*10);
        pacmanLoosesLive = new SoundClip("/sounds/pacman_looseslives.wav");
        pacmanKillsGhost = new SoundClip("/sounds/pacman_killsghost.wav");
        pacmanRestart = new SoundClip("/sounds/pacman_restart.wav");
        pacmanShoots = new SoundClip("/sounds/pacman_shoots.wav");
    }

    /**
     * initializing the game assets and objects
     **/
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        // generate player and ghosts
        player = new Player(getWidth() / 2 - 50, getHeight() - 100, 100, 100, this);
        ghosts = new ArrayList<Ghosts>();
        //create ghosts
        /*
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 5; j++) {
                ghosts.add(new Ghosts(10+i*(50+10), 10+j*50, 50, 50, this, 2));
            }
        }
        */
        ghostsCont = new GhostCont(this);
        ghostCol = 12;
        ghostRow = 5;
        
        //create bullets
        bullets = new ArrayList<Bullet>();
        enemyShot = new ArrayList<Bullet>();
        display.getJframe().addKeyListener(keyManager);
    }

    @Override
    public void run() {
        init();
        //frames per second
        int fps = 60;
        //time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        //initializing delta
        double delta = 0;
        //define now to use inside the loop
        long now;
        //initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();

        while (running) {
            //setting the time now to the actual time
            now = System.nanoTime();
            //acumulating to delta the difference between times in time Tick units
            delta += (now - lastTime) / timeTick;
            //updating the last time
            lastTime = now;

            //if delta is positive we tick the game
            if (delta >= 1) {
                if (!gameOver && !keyManager.pause) {
                    tick();
                }
                if(keyManager.pause) {
                    if(keyManager.save) {
                        Files.saveFile(this);
                    }
                    if(keyManager.load) {
                        Files.loadFile(this);
                    }
                    
                }
                render();
                delta--;
                if (gameOver && keyManager.restart) {
                    restart();
                }
            }
        }
        stop();
    }

    /**
     * function responsible for collision detection and movement of objects
     */
    private void tick() {
        keyManager.tick();
            
        // save and load file, only when paused
        if(keyManager.save && keyManager.pause) {
            Files.saveFile(this);
        }
        if(keyManager.load && keyManager.pause) {
            Files.loadFile(this);
        }
        
        player.tick();
        
        //create bullet 20px wide, 20px high
        if(keyManager.space) {
            bullets.add(new Bullet(player.getX()+player.getWidth()/2-10, 
                    player.getY()-10, 10, 10, this, -3));
            //play player shoots sound
            pacmanShoots.play();
        }
        for(int i = 0; i < ghostCol; i++) {
            for(int j = 0; j < ghostRow; j++) {
                Ghosts ghost = ghostsCont.getGhost(i, j);
                if(ghost != null) {
                    ghost.tick();
                    if(ghostsCont.ghostAct(i, j) && ghost.isAction()) {
                        enemyShot.add(new Bullet(ghost.getX()+ghost.getWidth()/2-10, 
                        ghost.getY()+ghost.getHeight(),10,10,this,3));
                    }
                }
            }
        }
        /*
        for(Ghosts ghost : ghosts) {
            ghost.tick();
            if (ghost.isAction()) {
                enemyShot.add(new Bullet(ghost.getX()+ghost.getWidth()/2-10, 
                        ghost.getY()+ghost.getHeight(),10,10,this,3));
            }
        }
        */
        //move bullets
        for(int i = 0; i < bullets.size(); i++) {
            Bullet bullet = (Bullet) bullets.get(i);
            bullet.tick();
            for(int j = 0; j < ghostCol; j++) {
                for(int k = 0; k < ghostRow; k++) {
                    Ghosts ghost = ghostsCont.getGhost(j, k);
                    if(bullet.intersects(ghost)) {
                        score += 10;
                        ghostsCont.ghostDeath(j, k);
                        bullets.remove(i);
                        --i;
                        break;
                    }
                    
                }
                
            }
            if(bullet.getY() <= 0) {
                bullets.remove(i);
                --i;
            }
            /*
            for(int j = 0; j < ghosts.size(); j++) {
                Ghosts ghost = (Ghosts) ghosts.get(j);
                if(bullet.intersects(ghost)) {
                    //play pacman kills ghost sound
                    pacmanKillsGhost.play();
                    score += 10;
                    ghosts.remove(j);
                    bullets.remove(i);
                    --i;
                    --j;
                    break;
                }
                if(ghost.getY() <= 0) {
                    ghosts.remove(j);
                    --j;
                }
            }
            */
        }
        
        for(int i = 0; i < enemyShot.size(); i++) {
            Bullet bullet = (Bullet) enemyShot.get(i);
            bullet.tick();
            if (bullet.intersects(player)) {
                //play pacman looses live sound
                pacmanLoosesLive.play();
                --lives;
                enemyShot.remove(i);
                --i;
            }
            
            if(bullet.getY() > this.getHeight()) {
                enemyShot.remove(i);
                --i;
            }
        }
        
            // game over on no lives or no bricks
        if (ghostsCont.getCant() <= 0 || lives <= 0) {
            gameOver = true;
        }        

    }
    
    /**
     * function that draws all objects into the display
     */
    private void render() {
            //get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* 
        if it is null, we define one with 3 buffers to display images of the 
        game, if not null, then we display every image of the game but after clearing 
        the Rectangle, getting the graphic object from the buffer strategy element.
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);
                // if game over, show end game images
            if (!gameOver) {
                    // paint player, ball, bricks and any booster
                player.render(g);
                for(Bullet bullet : bullets) {
                    g.setColor(Color.white);
                    bullet.render(g);
                }
                for(Bullet bullet : enemyShot) {
                    g.setColor(Color.red);
                    bullet.render(g);
                }
                /*
                for(Ghosts ghost : ghosts) {
                    ghost.render(g);
                }*/
                for(int i = 0; i < ghostCol; i++) {
                    for(int j = 0; j < ghostRow; j++) {
                        if(ghostsCont.getGhost(i, j) != null) {
                            ghostsCont.getGhost(i, j).render(g);
                        }
                    }
                }
                

                    // if paused, show pause image
                    // show save, load and continue options
                if (this.getKeyManager().pause) {
                    g.setColor(Color.white);
                    g.drawImage(Assets.pauseImage, (width / 2) - 300, 
                            (height / 2) - 130, 600, 300, null);
                    g.drawString("PRESS 'P' TO CONTINUE",
                            (width / 2) - 60, (height / 2) + 160);
                    g.drawString("PRESS 'S' TO SAVE",
                            (width / 2) - 45, (height / 2) + 180);
                    g.drawString("PRESS 'L' TO LOAD",
                            (width / 2) - 45, (height / 2) + 200);
                }
                //set font styles
                Font font1 = new Font("SansSerif", Font.BOLD, 20);
                g.setFont(font1);
                g.setColor(Color.white);
                //display lives and score
                g.drawString("LIVES: " + lives, 20, this.getHeight() - 20);
                g.drawString("SCORE: " + score, this.getWidth() - 130,
                        this.getHeight() - 20);
            } else {
                //Show image of game over
                if (lives == 0) {
                    g.drawImage(Assets.fail, 0, 0, width, height, null);
                } else {
                    //show image of "you win"
                    g.drawImage(Assets.win, 0, 0, width, height, null);
                }
            }
            bs.show();
            g.dispose();
        }
    }

    public int getMoveDist() {
        return moveDist;
    }
    
    /**
     * function to restart the game
     */
    private void restart() {
        lives = 3;
        gameOver = false;
        player = new Player(getWidth() / 2 - 50, getHeight() - 100, 
                100, 100, this);
       
        /*
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 5; j++) {
                ghosts.add(new Ghosts(10+i*(50+10), 10+j*50, 50, 50, this, 2));
            }
        }
        */
        //regenerate ghosts
        ghostsCont = new GhostCont(this);
       
        bullets = new ArrayList<Bullet>();
        enemyShot = new ArrayList<Bullet>();
    }

    /**

     * To get the width of the game window
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * get access to player
     * @return <code>player</code> object for the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * have access to the key manager
     * @return <code>KeyManager</code> object for keyboard management
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }

    /**
     * start the thread for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
