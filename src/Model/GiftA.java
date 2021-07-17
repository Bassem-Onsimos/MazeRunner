
package Model;

import Controller.Controller;
import View.Game;

public class GiftA extends Gift{
    
    public GiftA(int x, int y, Game game) {
        super(x, y, game);
        
        image = game.getGiftASprite();
        sound = game.getGiftSound();
    }
    
    @Override
    public void collected(){
        super.collected();
        //Put Armor On
        sound.play();
        Player player = game.getPlayer();
        player.setArmoured(true);
    }

    
}
