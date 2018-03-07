package video.game;

import java.awt.image.BufferedImage;

/**
 * @author diana.barrios 
 * @author NissimBetesh
 */

public class Assets {
    public static BufferedImage background;             //to store background image
    public static BufferedImage sprites;                //to store the sprites
    public static BufferedImage playerRight[];          //to store the player right images
    public static BufferedImage playerShoot[];          //to store the player shoot images
    public static BufferedImage playerLeft[];           //to store the player left images
    public static BufferedImage ghostRed[];             //to store the red ghosts   
    public static BufferedImage ghostPink[];            //to store the pink ghosts   
    public static BufferedImage ghostBlue[];            //to store the blue ghosts   
    public static BufferedImage ghostOrange[];          //to store the orange ghosts 
    public static BufferedImage ghostRedInverse[];      //to store the red inverse ghosts   
    public static BufferedImage ghostPinkInverse[];     //to store the pink inverse ghosts   
    public static BufferedImage ghostBlueInverse[];     //to store the blue inverse ghosts   
    public static BufferedImage ghostOrangeInverse[];   //to store the orange inverse ghosts 
    public static BufferedImage pauseImage;             //to store the pause image
    public static BufferedImage win;                    //to store victory image
    public static BufferedImage fail;                   //to store fail image
    
    
    
    public static void init(){
        background = ImageLoader.loadImage("/images/backgorund.jpg");
        pauseImage = ImageLoader.loadImage("/images/gamePaused.png");
        win = ImageLoader.loadImage("/images/win.png");
        fail = ImageLoader.loadImage("/images/gameOver.png");
        sprites = ImageLoader.loadImage("/images/pacmam.png");
        
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
        ghostRedInverse  = new BufferedImage[2];
        ghostPinkInverse  = new BufferedImage[2];
        ghostBlueInverse = new BufferedImage[2];
        ghostOrangeInverse = new BufferedImage[2];
        
        //cropping the pictures from the sheet into the array
        for (int i = 0; i < 2; i++){
            playerRight[i] = spritesheet.crop(i * 17, 0, 16, 16);
            playerLeft[i] = spritesheet.crop(i * 17, 16, 16, 16);
            playerShoot[i] = spritesheet.crop(i * 17, 32, 16, 16);
            ghostRed[i] = spritesheet.crop(i * 17, 64, 16, 16);
            ghostPink[i] = spritesheet.crop(i * 17, 80, 16, 16);
            ghostBlue[i] = spritesheet.crop(i * 17, 96, 16, 16);
            ghostOrange[i] = spritesheet.crop(i * 17, 112, 16, 16);
            ghostRedInverse[i] = spritesheet.crop((i * 17) + 33, 64, 16, 16);
            ghostPinkInverse[i] = spritesheet.crop((i * 17) + 33, 80, 16, 16);
            ghostBlueInverse[i] = spritesheet.crop((i * 17) + 33 , 96, 16, 16);
            ghostOrangeInverse[i] = spritesheet.crop((i * 17) + 33, 112, 16, 16);
        }
    }
}
