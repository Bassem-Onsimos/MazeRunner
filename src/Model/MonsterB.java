/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.Game;
import View.SpriteSheet;

/**
 *
 * @author shadyonsimos
 */
public class MonsterB extends Monster {
    
    public MonsterB(int x, int y, Game game) {
        super(x, y, game);
        
        dx = 1;
        dy = 1;
        
        maxStep = 5;
        ss = game.getMonsterBSprite();
        image = ss.cropImage(step, direction, tileSize, tileSize);
    }
    
}
