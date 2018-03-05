package video.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author diana.barrios NissimBetesh
 */
class Ball extends Item {
    private Game game;
    private int speedX;
    private int speedY;
    
    /**
     * create a ball
     * @param x <code>int</code> value for the x position
     * @param y <code>int</code> value for the y position
     * @param width <code>int</code> value for the width
     * @param height <code>int</code> value for the height
     * @param game <code>Game</code> object to access game resources
     * @param speedX <code>int</code> value for the horizontal speed
     * @param speedY <code>int</code> value for the vertical speed
     */
    public Ball(int x, int y, int width, int height, Game game, int speedX, 
            int speedY){
        super(x, y, width, height);
        this.game = game;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    /**
     * get the horizontal speed of the ball
     * @return <code>int</code> value of the speed
     */ 
    public int getSpeedX() {
        return speedX;
    }

    /**
     * get the vertical speed of the ball
     * @return <code>int</code> value of the speed
     */
    public int getSpeedY() {
        return speedY;
    }

    /**
     * set horizontal speed of ball
     * @param speedX <code>int</code> value of speed
     */
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    /**
     * set vertical speed of ball
     * @param speedY <code>int</code> value of speed
     */
    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }
    
    @Override
    public void tick() {
        setX(getX() + getSpeedX());
        setY(getY() + getSpeedY());
        
            // create wall 'collision'
            // invert the speed in X
        if (getX() + this.getWidth() >= game.getWidth()) {
            setX(game.getWidth() - this.getWidth());
            setSpeedX(getSpeedX() * -1);
        }
        else if (getX() <= 0) {
            setX(0);
            setSpeedX(getSpeedX() * -1);
        }
            // reset y position if colision with upper bound
        if (getY() <= 0){
            setY(0);
            setSpeedY(getSpeedY() * -1);
        }
    }

    @Override
    public void render(Graphics g) {
               g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);

    }
    
    public Ellipse2D.Double getEllipse() {
        return new Ellipse2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
