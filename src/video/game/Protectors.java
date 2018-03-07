/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author diana.barrios
 */
public class Protectors extends Item{

    private Game game;
    
    public Protectors(int x, int y, int width, int height, Game game){
        super(x, y, width, height);
        this.game = game;
    }
   
    @Override
    public void render(Graphics g) {
       g.setColor(Color.blue);
       g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void tick() {
        //
    }
}
