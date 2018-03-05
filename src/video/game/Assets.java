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
    
    
    public static void init(){
        background = ImageLoader.loadImage("/images/background.jpg");
        //use pacman
        player = ImageLoader.loadImage("/images/hank.png");
        //set pause
        pauseImage = ImageLoader.loadImage("/images/walter.png");
        win = ImageLoader.loadImage("/images/win.jpg");
        fail = ImageLoader.loadImage("/images/fail.png");
        //use ghost sprites
        brick = ImageLoader.loadImage("/images/meth.jpg");        
    }
}
