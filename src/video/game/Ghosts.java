package video.game;

/**
 *
 * @author diana.barrios NissimBetesh
 */

import java.awt.Graphics;


public class Ghosts extends Item{
    private Game game;
    private int speedX;
    private int moved;
    private int type;
    
    private Timer timer;
    private boolean action;
    
    private Animation animationRed;
    private Animation animationPink;
    private Animation animationBlue;
    private Animation animationOrange;
    private Animation animationRedReverse;
    private Animation animationPinkReverse;
    private Animation animationBlueReverse;
    private Animation animationOrangeReverse;
 
    
    /**
     * create a brick object
     * @param x <code>int</code> value for the x position
     * @param y <code>int</code> value for the y position
     * @param width <code>int</code> value for the width
     * @param height <code>int</code> value for the height
     * @param game <code>Game</code> object to access the game resources
     * @param speedX <code>int</code> value of horizontal speed
     */
    public Ghosts(int x, int y, int width, int height, Game game, int speedX) {
        super(x, y, width, height);
        this.game = game;
        this.speedX = speedX;
        moved = 0;
        type = (int) (Math.random()*4);
        if(type == 0) {
            this.animationRed = new Animation(Assets.ghostRed, 100);
            this.animationRedReverse = new Animation(Assets.ghostRedInverse, 100);
        }
        else if(type == 1) {
            this.animationPink = new Animation(Assets.ghostPink, 100);
            this.animationPinkReverse = new Animation(Assets.ghostPinkInverse, 100);
        }
        else if (type == 2) {
            this.animationBlue = new Animation(Assets.ghostBlue, 100);
            this.animationBlueReverse = new Animation(Assets.ghostBlueInverse, 100);
        }
        else {
            this.animationOrange = new Animation(Assets.ghostOrange, 100);
            this.animationOrangeReverse = new Animation(Assets.ghostOrangeInverse, 100);
        }
        timer = new Timer((int) (Math.random()*15 + 5));
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }    
    
    public boolean isAction() {
        return action;
    }
    
    @Override
    public void tick() {
        action = timer.isAction();
        
        setX(getX() + speedX);
        moved += speedX;
        
        if(Math.abs(moved) >= game.getMoveDist()) {
            moved = 0;
            setY(getY() + 5);
            speedX = -speedX;
        }
        
        if(type == 0) {
            this.animationRed.tick();
            this.animationRedReverse.tick();
        }
        else if(type == 1) {
            this.animationPink.tick();
            this.animationPinkReverse.tick();
        }
        else if (type == 2) {
            this.animationBlue.tick();
            this.animationBlueReverse.tick();
        }
        else {
            this.animationOrange.tick();
            this.animationOrangeReverse.tick();
        }
        timer.tick();
    }

    @Override
    public void render(Graphics g) {
        if(type == 0) {
            if(speedX > 0) {
                g.drawImage(animationRed.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            }
            else {
                g.drawImage(animationRedReverse.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            }
        }
        else if(type == 1) {
            if(speedX > 0) {
                g.drawImage(animationPink.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            }
            else {
                g.drawImage(animationPinkReverse.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            }
        }
        else if(type == 2) {
            if (speedX > 0) {
                g.drawImage(animationBlue.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            }
            else {
                g.drawImage(animationBlueReverse.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            }
        }
        else {
            if (speedX > 0) {
                g.drawImage(animationOrange.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            }
            else {
                g.drawImage(animationOrangeReverse.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            }
        }
    }
}