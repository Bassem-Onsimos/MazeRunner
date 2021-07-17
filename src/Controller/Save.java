
package Controller;

import Model.Collectable;
import Model.Monster;
import Model.Tile;
import View.Game;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Save {
    
    Game game;
    Controller controller;
    Formatter formatter = null;
    
    public Save(Game game) {
        this.game = game;
        controller = game.getController();
        
        writePlayerCoordinates();
        writeTiles();
        writeMonsters();
        writeCollectables();
        writeData();
        
    }

    public void writePlayerCoordinates() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/savedData/Player.txt"), "utf-8"));
                writer.write(game.getPlayer().x + ", " + game.getPlayer().y + ", " + game.getPlayer().isArmoured());
                writer.newLine();
        } catch (IOException ex) {
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
            }
        }
    }
    
    public void writeTiles() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("src/savedData/Tiles.txt"), "utf-8"));
            String type;
            for(int i=0; i<controller.getTiles().length;i++){
                for(int j=0; j<controller.getTiles()[0].length; j++){
                    Tile tile = controller.getTiles()[i][j];
                    if(tile!=null){
                        writer.write(i + ", " + j + ", " + tile.getClass().getSimpleName());
                        writer.newLine();
                    }
                }
            }
        } catch (IOException ex) {
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
            }
        }
    }
    
    public void writeMonsters() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/savedData/Monsters.txt"), "utf-8"));
            for(Monster m : controller.getMonsters()){
                writer.write(m.x + ", " + m.y + ", " + m.getClass().getSimpleName());
                writer.newLine();
            }    
            
        } catch (IOException ex) {
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
            }
        }
    }
    
    public void writeCollectables() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/savedData/Collectables.txt"), "utf-8"));
            for(Collectable c : controller.getCollectables()){
                writer.write(c.x + ", " + c.y + ", " + c.getClass().getSimpleName());
                writer.newLine();
            }    
            
        } catch (IOException ex) {
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
            }
        }
    }
    
    public void writeData() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/savedData/Data.txt"), "utf-8"));
            PlayerData data = game.getData();
            
            writer.write(data.getScore() + ", " + data.getHealth() + ", " + data.getLives() + ", " + data.getBullets() + ", " + data.getMinutes() + ", " + data.getSeconds());
            writer.newLine();
            
        } catch (IOException ex) {
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
            }
        }
    }
    
}
