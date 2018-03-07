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
        timer = new  Timer(Math.random()*11+4);
        this.setX (left ? 0 : game.getWidth() - width);
        this.animationUFO = new Animation(Assets.ufo, 100);
    }
    
    private void start() {
        left = (Math.random()*3 + 1) <= 2 ? true : false;
        if(left) {
            speedX = 2;
        }
        else {
            speedX = -2;
        }
        int[] scores = new int[]{50,100,150};
        score = scores[(int) (Math.random()*2)];
        
    }
    
    public int getScore() {
        return score;
    }
    
    public void die() {
        alive = false;
        timer = new Timer(Math.random()*11+4);
    }

    @Override
    public void tick() {
        if(timer.isAction()) {
            alive = true;
            start();
            this.setX (left ? 0 : game.getWidth() - this.getWidth()-2);
            timer.tick();
        }
        if(!alive) {
           this.setX(game.getWidth());
           speedX = 0;
           timer.tick();
        }
        setX(getX() + speedX);
        animationUFO.tick();
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
