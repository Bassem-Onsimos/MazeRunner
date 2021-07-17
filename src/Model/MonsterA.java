
package Model;

import View.Game;
import View.SpriteSheet;

public class MonsterA extends Monster{
    
    public MonsterA(int x, int y, Game game) {
        super(x, y, game);
        
        dx = 1;
        dy = 1;
        
        maxStep = 5;
        ss = game.getMonsterASprite();
        image = ss.cropImage(step, direction, tileSize, tileSize);
    }
    
    
    
}
