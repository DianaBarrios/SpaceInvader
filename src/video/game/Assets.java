package video.game;

import java.awt.image.BufferedImage;

/**
 *
 * @author diana.barrios NissimBetesh
 */
public class Assets {
    public static BufferedImage background;             //to store background image
    public static BufferedImage player;                 //to store player image
    public static BufferedImage sprites;                //to store the sprites
    public static BufferedImage playerRight[];          //to store the player right images
    public static BufferedImage playerShoot[];          //to store the player shoot images
    public static BufferedImage playerLeft[];           //to store the player left images
    public static BufferedImage ghostRed[];             //to store the red ghosts   
    public static BufferedImage ghostPink[];            //to store the pink ghosts   
    public static BufferedImage ghostBlue[];            //to store the blue ghosts   
    public static BufferedImage ghostOrange[];          //to store the orange ghosts   
    public static BufferedImage brick;                  //to store brick image
    public static BufferedImage pauseImage;             //to store the pause image
    public static BufferedImage win;                    //to store victory image
    public static BufferedImage fail;                   //to store fail image
    public static BufferedImage ball;                   //to store ball image
    
    
    public static void init(){
        background = ImageLoader.loadImage("/images/background.jpg");
        //use pacman
        player = ImageLoader.loadImage("/images/hank.png");
        pauseImage = ImageLoader.loadImage("/images/pause1.png");
        win = ImageLoader.loadImage("/images/win.jpg");
        fail = ImageLoader.loadImage("/images/fail.png");
        sprites = ImageLoader.loadImage("/images/pacmam.png");
        brick = ImageLoader.loadImage( "/images/meth.jpg");
        
        //Creating array of images before animations
        SpriteSheet spritesheet;
        spritesheet = new SpriteSheet(sprites);
        
        playerRight = new BufferedImage[2];
        playerLeft = new BufferedImage[2];
        playerShoot = new BufferedImage[2];
        
        ghostRed  = new BufferedImage[2];
        ghostPink  = new BufferedImage[2];
        ghostBlue  = new BufferedImage[2];
        ghostOrange = new BufferedImage[2];
        
        //cropping the pictures from the sheet into the array
        for (int i = 0; i < 2; i++){
            playerRight[i] = spritesheet.crop(i * 17, 0, 16, 16);
            playerLeft[i] = spritesheet.crop(i * 17, 16, 16, 16);
            playerShoot[i] = spritesheet.crop(i * 17, 32, 16, 16);
            ghostRed[i] = spritesheet.crop(i * 17, 64, 16, 16);
            ghostPink[i] = spritesheet.crop(i * 17, 80, 16, 16);
            ghostBlue[i] = spritesheet.crop(i * 17, 96, 16, 16);
            ghostOrange[i] = spritesheet.crop(i * 17, 112, 16, 16);
        }
    }
}
