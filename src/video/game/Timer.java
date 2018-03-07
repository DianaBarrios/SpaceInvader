package video.game;

/**
 * @author diana.barrios
 * @author NissimBetesh
 */
public class Timer {
    private long lastTime;          // to save the previous time of the animation
    private long timer;             // to acumulate the time of the animation
    int max;                        // max time to accumulate
    boolean action;                 // check if action should be done
    
    /**
     * starting the timer to delay actions
     * @param max <code>double</code> value with the time to act
     */
    public Timer(double max){
        timer = 0; 
        this.max = (int) max*1000;
            //getting the initial time
        lastTime = System.currentTimeMillis(); 
        action = false;
    }
   
    /**
     * return if action is to be done
     * @return <code>boolean</code> value with action to be done
     */
    public boolean isAction() {
        return action;
    }
    
    /**
     * add to the timer, when it reaches the max, action is to be done
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
