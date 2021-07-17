
package Controller;


import Model.Collectable;
import Model.EntityFactory;
import Model.Monster;
import Model.Tile;
import View.Game;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class NewLevel {
    private int width, height;
    private final int tileSize = 22;
    
    private EntityFactory factory;
    
    private int MonsterACount = 0;
    private int MonsterBCount = 0;
    
    private int BombACount = 0;
    private int BombBCount = 0;
    
    private int GiftACount = 0;
    private int GiftBCount = 0;
    
    private int coins = 0;
    
    private Tile[][] tiles;
        
    public NewLevel(Game game){
        factory = game.getFactory();
            
            width = 30;
            height = 30;
            
            MazeGenerator maze = new MazeGenerator(width, height);
            
            game.setWidth(width*tileSize);
            game.setHeight(height*tileSize);

            int[] pixels = maze.getMaze();
            tiles = new Tile[width][height];

            Controller controller = game.getController();
            
            for(int x=0; x<width-1; x++){
                for(int y=0; y<height-1; y++){
                    int current = pixels[x + (y*width)];

                    if((current & maze.getCELL_PATH_S())!=0)
                       tiles[x][y+1] = tiles[x][y] = (Tile) factory.createEntity("RockTile", x*tileSize, (y+1)*tileSize, game);
                       
                    if((current & maze.getCELL_PATH_E())!=0)
                       tiles[x+1][y] = tiles[x][y] = (Tile) factory.createEntity("RockTile", (x+1)*tileSize, y*tileSize, game);
                                     
                }
            }                        
    }
     
    public Tile[][] getTiles() {
        return tiles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    
    
}
