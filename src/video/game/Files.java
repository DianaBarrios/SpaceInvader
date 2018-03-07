package video.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author diana.barrios 
 * @author NissimBetesh
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
            
                // read number of ghosts
            line = br.readLine();
            parts = line.split(",");
            int numGhosts = Integer.parseInt(parts[0]);
            int moved = Integer.parseInt(parts[1]);
            int speedX = Integer.parseInt(parts[2]);
            
            GhostCont ghostCont = game.getGhostsCont();
            //GhostCont ghostCont = new GhostCont(game);
            game.getGhostsCont().setCant(numGhosts);
            
                // add ghosts to list
            for (int i = 0; i < 12; i++){
                for(int j = 0; j < 5; j++) {
                    line = br.readLine();
                    parts = line.split(",");
                    if(!"dead".equals(line)) {
                        int x = Integer.parseInt(parts[0]);
                        int y = Integer.parseInt(parts[1]);
                        Ghosts ghost = new Ghosts(x, y, 50, 50, game, 2);
                        ghost.setMoved(moved);
                        ghost.setSpeedX(speedX);
                        ghostCont.setGhost(i, j, ghost);
                    }
                    else {
                        ghostCont.setGhost(i, j, null);
                    }
                }
            }
            
            
            // get the enemy bullets
            line = br.readLine();
            int size = Integer.parseInt(line);
            game.getEnemyShot().clear();

            for(int i = 0; i < size; i++) {
                line = br.readLine();
                parts = line.split(",");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                game.getEnemyShot().add(new Bullet(x,y,10,10,game,3));
            }

            // load player bullets
            line = br.readLine();
            size = Integer.parseInt(line);
            game.getBullets().clear();
                
            for(int i = 0; i < size; i++) {
                line = br.readLine();
                parts = line.split(",");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                game.getBullets().add(new Bullet(x,y,10,10,game,-3));
            }
            
            line = br.readLine();
            parts = line.split(",");
            int score = Integer.parseInt(parts[0]);
            int lives = Integer.parseInt(parts[1]);
            game.setScore(score);
            game.setLives(lives);
            
        }catch (IOException ioe){
            System.out.println("No hay saves " + ioe.toString());
        }
    }
    
    public static void saveFile(Game game){
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("saves/Save.txt"));
                // save player
            writer.println("" + game.getPlayer().getX() + "," 
                    + game.getPlayer().getY());
            
                // save enemies. Enemy number, distance moved of enemy, enemies speedX
            writer.println("" + 60 + "," + 
                    game.getGhostsCont().getGhost(0, 0).getMoved() + "," +
                    game.getGhostsCont().getGhost(0, 0).getSpeedX());
                // store enemies position
            for(int i = 0; i < 12; i++) {
                for(int j = 0; j < 5; j++) {
                    if(game.getGhostsCont().getGhost(i, j) != null) {
                        writer.println("" + game.getGhostsCont().getGhost(i, j).getX()
                            + "," + game.getGhostsCont().getGhost(i, j).getY());
                    }
                    else {
                        writer.println("dead");
                    }
                }
            }
            
            // save enemy bullets
            writer.println("" + game.getEnemyShot().size());
            for(int i = 0; i < game.getEnemyShot().size(); i++) {
                writer.println("" + game.getEnemyShot().get(i).getX() + "," +
                        game.getEnemyShot().get(i).getY());
            }
            
            // save player bullets
            writer.println("" + game.getBullets().size());
            for(int i = 0; i < game.getBullets().size(); i++) {
                writer.println("" + game.getBullets().get(i).getX() + ","
                + game.getBullets().get(i).getY());
            }
            
            // save score and lives
            writer.println("" + game.getScore() + "," + game.getLives());
            
            writer.close();
        } catch (IOException ioe){
            System.out.println("Se lleno el Disco Duro, no se puede guardar" + 
                    ioe.toString());
        }
    }
    
}
