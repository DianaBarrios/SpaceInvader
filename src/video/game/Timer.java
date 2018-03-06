/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

/**
 *
 * @author Nissim
 */
public class Timer {
    private long lastTime;          //to save the previous time of the animation
    private long timer;             //to acumulate the time of the animation
    int max;                        //max time to accumulate
    boolean action;                 //check if action should be done
    
    /**
     * starting the timer
     */
    public Timer(int max){
        timer = 0; 
        this.max = max*1000;
            //getting the initial time
        lastTime = System.currentTimeMillis(); 
        action = false;
    }
   
    public boolean isAction() {
        return action;
    }
    
    /**
     * To update the animation to get the right index of the frame to paint
     */
    public void tick() {
        action = false;
            //acumulating time from the previous tick to this one
        timer += System.currentTimeMillis() - lastTime;
            //updating the lastTime for the next tick
        lastTime = System.currentTimeMillis();
            //check the timer to increase the index
        if(timer >= max) {
            timer = 0;
            action = true;
        }
    }
}
