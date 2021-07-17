
package Controller;

import Model.Collectable;
import Model.EntityFactory;
import Model.Monster;
import Model.Player;
import Model.Tile;
import View.Game;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Load {
    Game game;
    Controller controller;
    EntityFactory factory;
    int tileSize = 22;
    Tile[][] tiles = new Tile[31][31];

    public Load(Game game){
        this.game = game;
        controller = game.getController();
        factory = game.getFactory();
        
        controller.setHeight(31);
        controller.setWidth(31);
        game.setHeight(31*tileSize);
        game.setWidth(31*tileSize);
        
        if(!(readPlayerCoordinates() &&
                readTiles() &&
                readMonsters() &&
                readCollectables() &&
                readPlayerData()
                ))
                System.exit(0);
    }

    private boolean readPlayerCoordinates()
    {
        String FILENAME = "src/savedData/Player.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] parts = sCurrentLine.split(", ");
                game.setPlayerX(Integer.parseInt(parts[0]));
                game.setPlayerY(Integer.parseInt(parts[1]));
                
                if(parts[2].equals("true"))
                    game.setPlayerArmourState(true);
                else
                    game.setPlayerArmourState(false);
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private boolean readMonsters()
    {
        String FILENAME = "src/savedData/Monsters.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] parts = sCurrentLine.split(", ");
                controller.addMonster((Monster)factory.createEntity(parts[2], Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), game));
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private boolean readCollectables()
    {
        String FILENAME = "src/savedData/Collectables.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] parts = sCurrentLine.split(", ");
                controller.addCollectable((Collectable)factory.createEntity(parts[2], Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), game));
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private boolean readTiles()
    {
        String FILENAME = "src/savedData/Tiles.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] parts = sCurrentLine.split(", ");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                tiles[x][y] = (Tile)factory.createEntity(parts[2], x*tileSize, y*tileSize, game);
            }

            controller.setTiles(tiles);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private boolean readPlayerData()
    {
        String FILENAME = "src/savedData/Data.txt";
        PlayerData data = game.getData();
        
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] parts = sCurrentLine.split(", ");
                data.setScore(Integer.parseInt(parts[0]));
                data.setHealth(Integer.parseInt(parts[1]));
                data.setLives(Integer.parseInt(parts[2]));
                data.setBullets(Integer.parseInt(parts[3]));
                data.setMinutes(Integer.parseInt(parts[4]));
                data.setSeconds(Integer.parseInt(parts[5]));
            }
            
            game.setTime(data.getMinutes(), data.getSeconds());
            return true;
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
