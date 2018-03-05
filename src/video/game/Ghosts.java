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
        if(moved >= game.getMoveDist()) {
            moved = 0;
            setY(getY() + 5);
            speedX = -speedX;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.brick, getX(), getY(), getWidth(), getHeight(), null);
    }
}