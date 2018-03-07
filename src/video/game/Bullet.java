package video.game;

import java.awt.Graphics;

/**
 *
 * @author diana.barrios NissimBetesh
 */
class Bullet extends Item {
    private Game game;
    private int speedY;
    
    /**
     * create a ball
     * @param x <code>int</code> value for the x position
     * @param y <code>int</code> value for the y position
     * @param width <code>int</code> value for the width
     * @param height <code>int</code> value for the height
     * @param game <code>Game</code> object to access game resources
     */
    public Bullet(int x, int y, int width, int height, Game game, int speedY){
        super(x, y, width, height);
        this.game = game;
        this.speedY = speedY;
    }
    
    @Override
    public void tick() {
        setY(getY() + speedY);
    }

    @Override
    public void render(Graphics g) {
        //display bullet
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}
