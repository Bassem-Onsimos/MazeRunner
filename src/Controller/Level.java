
package Controller;


import Model.Collectable;
import Model.EntityFactory;
import Model.Monster;
import Model.Tile;
import View.Game;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Level {
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
        
    public Level(String path, Game game){
        factory = game.getFactory();
        try {
            BufferedImage map = ImageIO.read(getClass().getResource(path));
            width = map.getWidth();
            height = map.getHeight();
            
            game.setWidth(width*tileSize);
            game.setHeight(height*tileSize);

            int[] pixels = new int[width*height];
            tiles = new Tile[width][height];
            map.getRGB(0, 0, width, height, pixels, 0, width);
            Controller controller = game.getController();
            
            for(int x=0; x<width; x++){
                for(int y=0; y<height; y++){
                    int current = pixels[x + (y*width)];
                    
                    if(current == 0xFFFF0000){
                        //starting position
                        game.setPlayerX(x*tileSize);
                        game.setPlayerY(y*tileSize);
                    }
                    else if(current == 0xFF000000){
                        //Rock tile
                        tiles[x][y] = (Tile)factory.createEntity("RockTile", x*tileSize, y*tileSize, game);
                    }
                    else if(current == 0xFFFFFF00){
                        //wooden tile
                        tiles[x][y] = (Tile)factory.createEntity("WoodenTile", x*tileSize, y*tileSize, game);
                    }
                    else if(current == 0xFF0000FF){
                        //monster
                        
                        if(MonsterACount<=MonsterBCount){
                            controller.addMonster((Monster)factory.createEntity("MonsterA", x*tileSize, y*tileSize, game));
                            MonsterACount++;
                        }
                        else{
                            controller.addMonster((Monster)factory.createEntity("MonsterB", x*tileSize, y*tileSize, game));
                            MonsterBCount++;
                        }
                        
                    }
                    else if(current == 0xFF00FF00){
                        //bomb
                        if(BombACount<=BombBCount){
                            controller.addCollectable((Collectable)factory.createEntity("BombA", x*tileSize, y*tileSize, game));
                            BombACount++;
                        }
                        else{
                            controller.addCollectable((Collectable)factory.createEntity("BombB", x*tileSize, y*tileSize, game));
                            BombBCount++;
                        }
                    }
                    else if(current == 0xFF8BF0FF){
                        //gift
                        if(GiftACount<=GiftBCount){
                            controller.addCollectable((Collectable)factory.createEntity("GiftA", x*tileSize, y*tileSize, game));
                            GiftACount++;
                        }
                        else{
                            controller.addCollectable((Collectable)factory.createEntity("GiftB", x*tileSize, y*tileSize, game));
                            GiftBCount++;
                        }
                    }
                    else{
                        //coin
                        coins++;
                        if(coins%25==0)
                            controller.addCollectable((Collectable)factory.createEntity("Coin", x*tileSize, y*tileSize, game));
                    }                 
                }
            }
                              
        } catch (IOException e) {
            e.printStackTrace();
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
