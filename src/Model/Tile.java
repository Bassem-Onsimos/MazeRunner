
package Model;

import View.Game;
import java.awt.Graphics;

public class Tile extends Entity {

    public Tile(int x, int y, Game game) {
        super(x, y, game);
    }

    @Override
    public void tick(){
        
    }
    
    public void render(Graphics g){
        g.drawImage(image, x, y, game);
    }
    
}
