package video.game;

import java.awt.image.BufferedImage;

/**
 *
 * @author diana.barrios NissimBetesh
 */
public class Assets {
    public static BufferedImage background;             //to store background image
    public static BufferedImage player;                 //to store player image
    public static BufferedImage brick;                  //to store brick image
    public static BufferedImage pauseImage;             //to store the pause image
    public static BufferedImage win;                    //to store victory image
    public static BufferedImage fail;                   //to store fail image
    public static BufferedImage ball;                   //to store ball image
    public static BufferedImage plusOne[];              //to store plus one buff
    public static BufferedImage lengthPlus[];             //store increase length buff
    
    
    public static void init(){
        background = ImageLoader.loadImage("/images/labmeth.jpg");
        player = ImageLoader.loadImage("/images/hank.png");
        pauseImage = ImageLoader.loadImage("/images/walter.png");
        win = ImageLoader.loadImage("/images/win.jpg");
        fail = ImageLoader.loadImage("/images/fail.png");
        brick = ImageLoader.loadImage("/images/meth.jpg");
        plusOne = new BufferedImage[2];
        plusOne[0] = ImageLoader.loadImage("/images/plusone.png");
        plusOne[1] = ImageLoader.loadImage("/images/plusoneRev.png");
        lengthPlus = new BufferedImage[1];
        lengthPlus[0] = ImageLoader.loadImage("/images/plusSize.png");
        
    }
}
