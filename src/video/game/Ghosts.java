package video.game;

/**
 * @author diana.barrios 
 * @author NissimBetesh
 */

import java.awt.Graphics;

public class Ghosts extends Item{
    private Game game;
    private int speedX;     //  x speed of ghosts
    private int moved;      // distance moved
    private int type;       // type of enemy
    private Timer timer;    // timer to calculate when to shoot next
    private boolean action;                         // store if action is to be done
    private Animation animationRed;                 // animation for red enemy
    private Animation animationPink;                // animation for pink enemy
    private Animation animationBlue;                // animation for blue enemy 
    private Animation animationOrange;              // animation for orange enemy
    private Animation animationRedReverse;          // reverse animation for red
    private Animation animationPinkReverse;         // reverse animation for pink
    private Animation animationBlueReverse;         // reverse animation for blue
    private Animation animationOrangeReverse;       // reverse animation for orange
 
    
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
            // randomize type of enemy
        type = (int) (Math.random()*4);
            // set animation to use
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
        timer = new Timer((int) (Math.random()*7 + 3));
    }
    
    /**
     * get horizontal speed
     * @return <code>int</code> value of speed
     */
    public int getSpeedX() {
        return speedX;
    }

    /**
     * set the horizontal speed of enemy
     * @param speedX <code>int</code> value of new speed
     */
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }    
    
    /**
     * get if action will be done
     * @return <code>boolean</code> value of whether the action will be done
     */
    public boolean isAction() {
        return action;
    }
    
    /**
     * get distance moved
     * @return <code>int</code> value of the distance moved
     */
    public int getMoved() {
        return moved;
    }
    
    /**
     * set the distance moved
     * @param moved <code>int</code> new value of moved
     */
    public void setMoved(int moved) {
        this.moved = moved;
    }
    
    @Override
    public void tick() {
        action = timer.isAction();
        
        setX(getX() + speedX);
        moved += speedX;
        
            // if distance to be moved is max, move the enemies one step and invert speed
        if(Math.abs(moved) >= game.getMoveDist()) {
            moved = 0;
            setY(getY() + 5);
            speedX = -speedX;
        }
        
            // animate animation according to type
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
