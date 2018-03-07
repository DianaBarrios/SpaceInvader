package video.game;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author diana.barrios 
 * @author NissimBetesh
 */

public abstract class Item {
    protected int x;                    // to store x position
    protected int y;                    // to store y position
    protected int width;                // to store width
    protected int height;               // to store height
    
    /**
     * Set the initial values to create the item
     * @param x <b>x</b> position of the object
     * @param y <b>y</b> position of the object
     */
    public Item(int x, int y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Get x value
     * @return <code>int</code> value of the position
     */
    public int getX() {
        return x;
    }
    
    /**
     * Get y value
     * @return <code>int</code> value of the position
     */
    public int getY() {
        return y;
    }
    
    /**
     * Get width value
     * @return <code>int</code> value of the width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Get height value
     * @return <code>int</height> value of the height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Set x value
     * @param x <code>int</code> value to change
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Set y value
     * @param y <code>int</code> value to modify
     */
    public void setY(int y) {
        this.y = y;
    }
    
    
     /**
     * Set width value
     * @param width <code>int</code> value to modify
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * Set height value
     * @param height <code>int</code> value to modify
     */
    public void setHeight(int height) {
        this.height = height;
    }
   
    /**
     * To update the status, position of the item
     */
    public abstract void tick();
    
    /**
     * To paint the item
     * @param g <b>Graphics</b> object to paint the item
     */
    public abstract void render(Graphics g);
    
    /**
     * To get the bounds of the rectangle
     * @return <code>Rectange</code> rectangle on top of object
     */
    public Rectangle getBounds(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    /**
     * To check if the Rectangle intersects an object
     * @return <code>boolean</code> value confirming intersection
     */
    public boolean intersects(Object obj) {        
        return (obj instanceof Object && 
            this.getBounds().intersects(((Item) obj).getBounds()));
    }
}
