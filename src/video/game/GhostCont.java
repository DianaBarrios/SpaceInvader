/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.util.ArrayList;

/**
 * 
 * @author Nissim
 * @author Diana
 */
public class GhostCont {
    private ArrayList<ArrayList<Ghosts> > ghosts;
    private int cant;
    private Game game;
    
    public GhostCont(Game game) {
        ghosts = new ArrayList<ArrayList<Ghosts> >();
        
        for(int i = 0; i < 12; i++) {
            ghosts.add(new ArrayList<Ghosts>());
            for(int j = 0; j < 5; j++) {
                ghosts.get(i).add(new Ghosts(10+i*(50+10), 10+j*50, 50, 50, game, 2));
            }
        }
        cant = 60;
    }
    
    /*
    * return number of ghosts in the arraylist
    */
    public int getCant() {
        return cant;
    }
    
    public Ghosts getGhost(int i, int j) {
        return ghosts.get(i).get(j);
    }
    
    /**
     * make ghost position null, reduce cant
     * @param i
     * @param j
     */
    public void ghostDeath(int i, int j) {
        ghosts.get(i).set(j, null);
        --cant;
    }
    
    public boolean ghostAct(int i, int j) {
        int x = 4;
        
        while(x > j) {
            if(ghosts.get(i).get(x) != null) {
                return false;
            }
            x--;
        }
        return true;
     }
}
