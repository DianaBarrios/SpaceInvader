package video.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author diana.barrios NissimBetesh
 */
public class Player extends Item {
    
    private Game game;
    
    /**
     * To build a player object
     * @param x an <code>int</code> value to get the x coordinate
     * @param y an <code>int</code> value to get the y coordinate
     * @param direction an <code>int</code> value to get the direction
     * @param width an <code>int</code> value to get the width
     * @param height an <code>int</code> value to get the height
     * @param game a <code>game</code> object to get outside elements
     */
    public Player(int x, int y, int direction, int width, int height,
            Game game){
        super(x, y, width, height);
        this.game = game;
    }
    
    /**
     * create a rectangle around the player to detect collision
     * @return <code>Rectangle</code> object on top of player
     */
    public Rectangle getRectangle(){
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void tick() {
            //moving player depending on flags
        if (game.getKeyManager().left){
            setX(getX() - 5);
        }
        if (game.getKeyManager().right){
            setX(getX() + 5);
        }
        
            //reset x position and y position if collision with walls
        if (this.getX() + this.getWidth() >= game.getWidth()){
            setX(game.getWidth() - this.getWidth());
        } else if (getX() <= 0){
            setX(0);
        }
    }
        
    @Override
    public void render(Graphics g) {
       //display pacman
       g.setColor(Color.blue);
       g.fillRect(getX(), getY(), getWidth(), getHeight());
    } 
}
