/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.awt.Graphics;

/**
 *
 * @author Nissim
 */
public class UFO extends Item {
    private int score;
    private boolean left;
    private boolean alive;
    private int speedX;
    private Game game;
    private Timer timer;
    
    private Animation animationUFO;
    
    public UFO(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        start();
        alive = false;
        timer = new  Timer(Math.random()*10+3);
        this.setX (left ? 0 : game.getWidth() - this.getWidth());
        this.animationUFO = new Animation(Assets.ufo, 100);
    }
    
    private void start() {
        left = (Math.random()*2 + 1) <=1 ? true : false;
        if(left) {
            speedX = 3;
        }
        else {
            speedX = -3;
        }
        int[] scores = new int[]{50,100,150};
        score = scores[(int) (Math.random()*2)];
        
    }
    
    public void die() {
        alive = false;
    }

    @Override
    public void tick() {
        if(timer.isAction()) {
            alive = true;
            start();
            this.setX (left ? 0 : game.getWidth() - this.getWidth());
        }
        if(!alive) {
           timer = new Timer(Math.random()*10+3);
           this.setX(game.getWidth());
           speedX = 0;
        }
        setX(getX() + speedX);
        timer.tick();
        animationUFO.tick();
    }

    @Override
    public void render(Graphics g) {
        if(alive) {
            g.drawImage(animationUFO.getCurrentFrame(), getX(), getY(), 
                    getWidth(), getHeight(), null);
        }
        
    }
}
