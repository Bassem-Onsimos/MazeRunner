
package Model;

import View.Game;

public class WoodenTile extends Tile{
    
    public WoodenTile(int x, int y, Game game) {
        super(x, y, game);
        image = game.getWoodenTileSprite();
    }
    
}
