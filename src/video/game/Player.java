package video.game;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author diana.barrios NissimBetesh
 */
public class Player extends Item {
    
    private Game game;
    private Animation animationRight;           // to store the animation for going right
    private Animation animationLeft;            // to store the animation for going left
    private Animation animationShoot;           // to store the animation for going down
    
    private Timer timer;                        // timer between actions
    private boolean action;                     // whether action will be done
    
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
        timer = new Timer(0.3);
        action = false;
    }
    
    /**
     * create a rectangle around the player to detect collision
     * @return <code>Rectangle</code> object on top of player
     */
    public Rectangle getRectangle(){
        return new Rectangle(x, y, width, height);
    }
    
    /**
     * get whether action will be done
     * @return <code>boolean</code> value with whether or not to shoot
     */
    public boolean isAction() {
        return action;
    }

    @Override
    public void tick() {
            // when timer is finished, prepare action
        if(timer.isAction()) {
            action = true;
        }
            // if action is prepared and space is pressed, shoot
        if(action && game.getKeyManager().space) {
            game.shoot();
                // new timer to count
            timer = new Timer(0.6);
            action = false;
        }
            // moving player animation depending on flags
        this.animationShoot.tick(); 
        
        if (game.getKeyManager().left){
            setX(getX() - 5);
            animationLeft.tick();
        }
        if (game.getKeyManager().right){
            setX(getX() + 5);
            animationRight.tick();
        }
        
            // reset x position and y position if collision with walls
        if (this.getX() + this.getWidth() >= game.getWidth()){
            setX(game.getWidth() - this.getWidth());
            
        } else if (getX() <= 0){
            setX(0);
        }
        timer.tick();
    }
        
    public boolean intersectsGhost(Object obj) {        
        return (obj instanceof Ghosts && 
            this.getBounds().intersects(((Item) obj).getBounds()));
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
        else {
            g.drawImage(animationShoot.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }

    } 
}
