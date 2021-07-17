
package Model;

import Controller.PlayerData;
import View.Game;

public class GiftB extends Gift{
    
    public GiftB(int x, int y, Game game) {
        super(x, y, game);
        
        image = game.getGiftBSprite();
        sound = game.getRefillWeaponSound();
    }
    
    @Override
    public void collected(){
        super.collected();
        sound.play();
        PlayerData data = game.getData();
        data.setBullets(data.getBullets()+100);        
    }
    
    

}
