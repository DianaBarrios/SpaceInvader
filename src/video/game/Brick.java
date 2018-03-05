package video.game;

/**
 *
 * @author diana.barrios NissimBetesh
 */

import java.awt.Color;
import java.awt.Graphics;


public class Brick extends Item{
    private Game game;
    
    /**
     * create a brick object
     * @param x <code>int</code> value for the x position
     * @param y <code>int</code> value for the y position
     * @param width <code>int</code> value for the width
     * @param height <code>int</code> value for the height
     * @param game <code>Game</code> object to access the game resources
     */
    public Brick(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.brick, getX(), getY(), getWidth(), getHeight(), null);
    }
}