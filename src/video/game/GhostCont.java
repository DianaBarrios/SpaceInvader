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
    private ArrayList<ArrayList<Ghosts> > ghosts;       // store enemies
    private int cant;                                   // number of enemies left
    private Game game;
    
    public GhostCont(Game game) {
            // generate enemies
        ghosts = new ArrayList<ArrayList<Ghosts> >();
        for(int i = 0; i < 12; i++) {
            ghosts.add(new ArrayList<Ghosts>());
            for(int j = 0; j < 5; j++) {
                ghosts.get(i).add(new Ghosts(10+i*(50+10), 10+j*50, 50, 50, game, 2));
            }
        }
        cant = 60;
    }
    
    /**
     * get the number of enemies
     * @return <code>int</code> number of enemies
     */
    public int getCant() {
        return cant;
    }
    
    /**
     * set the current number of enemies
     * @param cant <code>int</code> new number of enemies
     */
    public void setCant(int cant) {
        this.cant = cant;
    }
    
    /**
     * get the ghost in the container
     * @param i <code>int</code> enemy in position (i,j)
     * @param j <code>int</code> enemy in position (i,j)
     * @return <code>Ghosts</code> enemy object
     */
    public Ghosts getGhost(int i, int j) {
        return ghosts.get(i).get(j);
    }
    
    /**
     * set the enemy in position (i,j)
     * @param i <code>int</code> position of enemy
     * @param j <code>int</code> position of enemy
     * @param ghost <code>Ghosts</code> new object
     */
    public void setGhost(int i, int j, Ghosts ghost) {
        ghosts.get(i).set(j, ghost);
    }
    
    /**
     * make ghost position null, reduce cant
     * @param i <code>int</code> position of enemy
     * @param j <code>int</code> position of enemy
     */
    public void ghostDeath(int i, int j) {
        ghosts.get(i).set(j, null);
        --cant;
    }
    
    /**
     * check if enemy is the last on the column, so it shoots
     * @param i <code>int</code> position of enemy
     * @param j <code>int</code> position of enemy
     * @return <code>boolean</code> whether to shoot
     */
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
    
    /**
     * get matrix with enemies
     * @return <code>ArrayList</code> list with the enemies
     */
    public ArrayList getGhosts() {
        return ghosts;
    }
}
