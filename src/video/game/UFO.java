/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.awt.Graphics;

/**
 * @author Diana.Barrios
 * @author Nissim
 */
public class UFO extends Item {
    private int score;                      // score of the current ufo
    private boolean left;                   // generated side
    private boolean alive;                  // if ufo is alive
    private int speedX;                     // horizontal speed of enemy
    private Game game;                      // game object
    private Timer timer;                    // timer for creation
    
    private Animation animationUFO;         // ufo move animation
    
    public UFO(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
            // start variables
        start();
        alive = false;
            // create timer
        timer = new  Timer(Math.random()*11+4);
            // generate position
        this.setX (left ? 0 : game.getWidth() - width);
        this.animationUFO = new Animation(Assets.ufo, 100);
    }
    
    /**
     * generate new variables for "living" ufo
     */
    private void start() {
            // randomize start position and set speed
        left = (Math.random()*3 + 1) <= 2 ? true : false;
        if(left) {
            speedX = 2;
        }
        else {
            speedX = -2;
        }
            // set score of the ufo
        int[] scores = new int[]{50,100,150};
        score = scores[(int) (Math.random()*2)];
        
    }
    
    /**
     * get score of the ufo
     * @return <code>int</code> value of the score
     */
    public int getScore() {
        return score;
    }
    
    /**
     * 'kill' the ufo, reset the timer
     */
    public void die() {
        alive = false;
        timer = new Timer(Math.random()*11+4);
    }

    @Override
    public void tick() {
            // if timer is to activate, 'live' the ufo and reset position
        if(timer.isAction()) {
            alive = true;
            start();
            this.setX (left ? 0 : game.getWidth() - this.getWidth()-2);
                // to not get stuck in action of timer
            timer.tick();
        }
            // if the ufo is dead, place outside screen and set speed 0
        if(!alive) {
           this.setX(game.getWidth());
           speedX = 0;
           timer.tick();
        }
            // move ufo and animation
        setX(getX() + speedX);
        animationUFO.tick();
            // 'kill' ufo if it leaves screen
        if(this.getX() < - getWidth() || getX() > game.getWidth() + 10) {
            die();
        }
    }

    @Override
    public void render(Graphics g) {
        if(alive) {
            g.drawImage(animationUFO.getCurrentFrame(), getX(), getY(), 
                    getWidth(), getHeight(), null);
        }
        
    }
}
