
package Model;

import Controller.Controller;
import Controller.PlayerData;
import View.Game;
import View.Sound;
import View.SpriteSheet;

public class Coin extends Collectable{
    
    private boolean spin = false;
    
    public Coin(int x, int y, Game game) {
        super(x, y, game);
        
        ss = game.getCoinSprite();
        
        sound = game.getCoinSound();
        
        step = 1;
        maxStep = 11;
    }
    
    @Override
    public void tick(){
        if(spin){
            step++;
            if(step==maxStep)
                step = 1;
            image = ss.cropImage(step, 1, tileSize, tileSize);
            spin = false;
        }
        else
            spin = true;
    }
    
    @Override
    public void collected(){
        super.collected();
        sound.play();
        PlayerData data = game.getData();
        data.setScore(data.getScore()+50);
    }
    
    public Sound getSound(){
        return this.sound;
    }
    
}
