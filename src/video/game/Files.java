package video.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author diana.barrios NissimBetesh
 */
public class Files {
    
    public static void loadFile(Game game){
        BufferedReader br = null;
        FileReader fr = null;
        
        try{
            String line;
            String[] parts;
            fr = new FileReader("saves/Save.txt");
            br = new BufferedReader(fr);
            
                // read first line
            line = br.readLine();
            parts = line.split(",");
            
                // read Player position
            game.getPlayer().setX(Integer.parseInt(parts[0]));
            game.getPlayer().setY(Integer.parseInt(parts[1]));
            
                // read number of bricks
            line = br.readLine();
            int NumBri = Integer.parseInt(line);
            
                // clean bricks
            game.getBricks().clear();
            
                // add bricks to list
            for (int i = 0; i < NumBri; i++){
                line = br.readLine();
                parts = line.split(",");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                
                Brick brick = new Brick(x, y, game.getWidth()/10, 25, game);
                        
                game.getBricks().add(brick);
                
            }
        }     
         catch (IOException ioe){
            System.out.println("No hay saves " + ioe.toString());
        }
    }
    
    public static void saveFile(Game game){
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("saves/Save.txt"));
            writer.println("" + game.getPlayer().getX() + "," 
                    + game.getPlayer().getY());
            writer.println("" + game.getBricks().size());
            for (Brick brick : game.getBricks()){
                writer.println("" + brick.getX() + "," + brick.getY());
            }
            writer.close();
        } catch (IOException ioe){
            System.out.println("Se lleno el Disco Duro, no se puede guardar" + 
                    ioe.toString());
        }
    }
    
}
