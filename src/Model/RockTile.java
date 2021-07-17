
package Model;

import View.Game;
import java.awt.Graphics;
import java.awt.Rectangle;

public class RockTile extends Tile {
    
    public RockTile(int x, int y, Game game){
        super(x, y, game);
        image = game.getRockTileSprite();
    }
    
    
    
}
