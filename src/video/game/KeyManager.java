package video.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author diana.barrios NissimBetesh
 */
public class KeyManager implements KeyListener{

    public boolean left;                // flag to move left the player
    public boolean right;               // flag to move right the player
    public boolean space;               // flag to shoot
    public boolean pause;               // to pause the game
    public boolean enter;               // to start new game
    public boolean restart;             // press r to restart
    private boolean keys[];             // to store all the flags for every key
    public boolean save;                // to save game
    public boolean load;                // to load game
    
    
    public KeyManager(){
        keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
            //set true to every key pressed
        keys[e.getKeyCode()] = true;
        if(keys[KeyEvent.VK_S]) {
            save = true;
        }
        if(keys[KeyEvent.VK_L]) {
            load = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            pause = !pause;
        }
        
        if(keys[KeyEvent.VK_R]) {
            restart = true;
        }
        if(keys[KeyEvent.VK_S]) {
            save = false;
        }
        if(keys[KeyEvent.VK_L]) {
            load = false;
        }
            //set false to every key released
        keys[e.getKeyCode()] = false; 
        
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick(){
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        space = keys[KeyEvent.VK_SPACE];
        enter = keys[KeyEvent.VK_ENTER];
        if (space){
            keys[KeyEvent.VK_SPACE] = false;
        }
        restart = keys[KeyEvent.VK_R];
        save = keys[KeyEvent.VK_S];
        load = keys[KeyEvent.VK_L];
    }
    
}
