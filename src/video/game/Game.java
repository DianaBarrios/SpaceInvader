package video.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author diana.barrios NissimBetesh
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
    private boolean started;                // to start the game
    private Player player;                  // to use a player
    private Ball ball;                      // to use the ball
    private ArrayList<Brick> bricks;        // to store bricks
    //private ArrayList<Booster> boosters;    // to store the boosters for the player
    private KeyManager keyManager;          // to manage the keyboard
    private boolean gameOver;               // to end the game
    private int lives;                      // number of lives for the player
    SoundClip playerChoque;                 // play sound on ball hit
    Random random;                          // random for booster

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
        started = false;
        keyManager = new KeyManager();
        gameOver = false;
        lives = 3;
        random = new Random();
        random.setSeed(System.nanoTime());
    }

    /**
     * initializing the game assets and objects
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
            // 100x100px pad
        player = new Player(getWidth() / 2 - 50, getHeight() - 100, 100,
                100, this);
        ball = new Ball(getWidth() / 2 - 10, getHeight() - player.getHeight() - 20,
                30, 30, this, 0, 0);
        bricks = new ArrayList<Brick>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                int width_brick = getWidth() / 10;
                Brick brick = new Brick(i * width_brick + 2, 30 * j + 5,
                        width_brick - 10, 25, this);
                bricks.add(brick);
            }
        }
        //boosters = new ArrayList<Booster>();
        playerChoque = new SoundClip("/sounds/explosion.wav");
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
            // if just started and pressed space, release ball
        if (this.getKeyManager().space && !this.isStarted()) {
            ball.setSpeedX(3);
            ball.setSpeedY(-4);
            this.setStarted(true);
        }
            // if the game was not started, permit movement of player and ball as one
        if (!this.isStarted()) {
            player.tick();
            ball.setX(player.getX() + player.getWidth() / 2 - ball.getWidth() / 2);
            ball.setY(player.getY() - ball.getHeight());
        } else {
            player.tick();
            ball.tick();
        }
            // checking ball crash with bricks
        for (int i = 0; i < bricks.size(); i++) {
            Brick brick = (Brick) bricks.get(i);
            if (brick.intersects(ball)) {
                    // if ball collides on right or left wall, invert X speed
                    // if ball collides on top or bot wall invert Y
                if((ball.getX() + ball.getWidth() >= brick.getX() || 
                        brick.getX() + brick.getWidth() <= ball.getX()) && 
                        (ball.getY() + ball.getHeight()/2 <= brick.getHeight() + 
                        brick.getY() && ball.getY() + ball.getHeight()/2 >= brick.getY())) {
                    ball.setSpeedX(ball.getSpeedX() * -1);
                }
                if((ball.getY() + ball.getHeight() >= brick.getY() ||
                        brick.getY() + brick.getHeight() >= ball.getY()) &&
                        (ball.getX() + ball.getWidth()/2 >= brick.getX() &&
                        brick.getX() + brick.getWidth() >= ball.getX())) {
                    ball.setSpeedY(ball.getSpeedY() * -1);
                }
                    // if speedX and speedY changed, means a border was hit,
                    // if so, exchange the speeds
                playerChoque.play();
                    // 10% probability of generating booster 
                
                bricks.remove(brick);                    
            }
        }
            // make boosters fall
        //for(Booster booster : boosters) {
        //    booster.tick();
        //}
            // if ball hits floor lose a life, shorten pad width and reset ball position
        if (ball.getY() + ball.getHeight() > this.getHeight()) {
            ball.setY(ball.getY() - 1);
            setStarted(false);
            lives--;
        }
            // game over on no lives or no bricks
        if (bricks.isEmpty() || lives == 0) {
            gameOver = true;
        }
            //change the direction in y of the ball when it hits the player
            // if ball hits the edges, 
        if (player.intersects(ball)) {
            if(player.getX() + 10 > ball.getX() ||
                    ball.getX() >= player.getX() + player.getWidth() - 10) {
                ball.setSpeedX(ball.getSpeedX()*-1);
            }
            if(player.getY() + player.getHeight()/2 > ball.getY() + ball.getHeight()/2) {
                ball.setSpeedY(ball.getSpeedY() * -1);
            }
            //sonido2.play(); 
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
                ball.render(g);
                for(Brick brick : bricks) {
                    brick.render(g);
                }
                    // if paused, show pause image
                    // show save, load and continue options
                if (this.getKeyManager().pause) {
                    g.drawImage(Assets.pauseImage, (width / 2) - 150, 
                            (height / 2) - 150, 300, 300, null);
                    g.drawString("PRESS 'P' TO CONTINUE",
                            (width / 2) - 60, (height / 2) + 160);
                    g.drawString("PRESS 'S' TO SAVE",
                            (width / 2) - 45, (height / 2) + 180);
                    g.drawString("PRESS 'L' TO LOAD",
                            (width / 2) - 45, (height / 2) + 200);
                }
                g.drawString("Lives: " + lives, 20, this.getHeight() - 20);
            } else {
                if (lives == 0) {
                    g.drawImage(Assets.fail, 0, 0, width, height, null);
                } else {
                    g.drawImage(Assets.win, 0, 0, width, height, null);
                }
            }
            bs.show();
            g.dispose();
        }
    }
    
    /**
     * function to restart the game
     */
    private void restart() {
        lives = 3;
        started = false;
        gameOver = false;
        player = new Player(getWidth() / 2 - 50, getHeight() - 100, 100, 
                20, this);
            // 20x20px ball on pad
        ball = new Ball(getWidth() / 2 - 10, getHeight() - player.getHeight() - 20,
                30, 30, this, 0, 0);
        bricks = new ArrayList<Brick>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                int width_brick = getWidth() / 10;
                Brick brick = new Brick(i * width_brick + 2, 30 * j + 5,
                        width_brick - 10, 25, this);
                bricks.add(brick);
            }
        }    
    }

    /**
     * get the list of bricks
     * @return <code>ArrayList</code> a list of bricks
     */
    public ArrayList<Brick> getBricks() {
        return bricks;
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
     * To know if the game has started
     * @return an <code>boolean</code> value with started or not
     */
    public boolean isStarted() {
        return started;
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
     * set whether game has just started
     * @param b <code>boolean</code> value to set
     */
    private void setStarted(boolean b) {
        this.started = b;
    }

    /**
     * start the thread for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            //pauseGame = false;
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
