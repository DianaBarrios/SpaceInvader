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
    
    private Animation animationRed;
    private Animation animationPink;
    private Animation animationBlue;
    private Animation animationOrange;
    
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
        this.animationRed = new Animation(Assets.ghostRed, 100);
        this.animationPink = new Animation(Assets.ghostPink, 100);
        this.animationBlue = new Animation(Assets.ghostBlue, 100);
        this.animationOrange = new Animation(Assets.ghostOrange, 100);
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }    
    
    @Override
    public void tick() {
        setX(getX() + speedX);
        moved += speedX;
        if(Math.abs(moved) >= game.getMoveDist()) {
            moved = 0;
            setY(getY() + 5);
            speedX = -speedX;
        }
        animationRed.tick();
        animationBlue.tick();
        animationPink.tick();
        animationOrange.tick();
    }

    @Override
    public void render(Graphics g) {
        if(type == 0) {
            g.drawImage(animationRed.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
        else if(type == 1) {
            g.drawImage(animationPink.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
        else if(type == 2) {
            g.drawImage(animationBlue.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
        else {
            g.drawImage(animationOrange.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
    }
}