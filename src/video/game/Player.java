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
    private Animation animationRight; //to store the animation for going right
    private Animation animationLeft; // to store the animation for going left
    private Animation animationShoot; //to store the animation for going down
    
    /**
     * To build a player object
     * @param x an <code>int</code> value to get the x coordinate
     * @param y an <code>int</code> value to get the y coordinate
     * @param width an <code>int</code> value to get the width
     * @param height an <code>int</code> value to get the height
     * @param game a <code>game</code> object to get outside elements
     */
    public Player(int x, int y, int width, int height, Game game){
        super(x, y, width, height);
        this.game = game;
        this.animationRight = new Animation(Assets.playerRight, 100);
        this.animationLeft = new Animation(Assets.playerLeft, 100);
        this.animationShoot = new Animation(Assets.playerShoot, 100);
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
        this.animationShoot.tick(); 
        
        if (game.getKeyManager().left){
            setX(getX() - 5);
            animationLeft.tick();
        }
        if (game.getKeyManager().right){
            setX(getX() + 5);
            animationRight.tick();
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
        if(game.getKeyManager().left) {
            g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
        else if (game.getKeyManager().right) {
            g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
        else /*(game.getKeyManager().space) */{
            g.drawImage(animationShoot.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }

    } 
}
