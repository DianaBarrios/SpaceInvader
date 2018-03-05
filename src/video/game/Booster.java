package video.game;

import java.awt.Graphics;
import java.util.Random;

/**
 * @author diana.Barrios NissimBetesh
 */
public class Booster extends Item {
        // 1 is extra life
        // 2 is increase pad length
    private int boosterType;            // value for the type of booster
    private Animation animation;        // animation order
    private int C;                      // tick animation
    
    public Booster(int x, int y, int width, int height, Game game) {
        super(x,y,width,height);
            // chance of booster type
        Random random = new Random();
        random.setSeed(System.nanoTime());
        boosterType = random.nextInt(2)+1;
        if(boosterType == 1) {
            this.animation = new Animation(Assets.plusOne, 100);
        }
        else if(boosterType == 2) {
            this.animation = new Animation(Assets.lengthPlus, 100);
            System.out.println(boosterType);        }
        C = 0;
    }

    /**
     * get the type of booster
     * @return <code>int</code> value
     */
    public int getBoosterType() {
        return boosterType;
    }
    
    @Override
    public void tick() {
            // speed of booster fall
        this.setY(this.getY() + 3);
        if(C >= 20) {
            C = 0;
            this.animation.tick();
        }
        
        
        C++;
    }

    @Override
    public void render(Graphics g) {
        if(boosterType == 1) {
            g.drawImage(animation.getCurrentFrame(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
        }
        else if(boosterType == 2) {
            g.drawImage(animation.getCurrentFrame(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
        }
        
    }
}
