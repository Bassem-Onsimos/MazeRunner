
package Model;

import View.BufferedImageLoader;
import View.Game;
import View.SpriteSheet;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Armor extends PlayerDecorator{
    
    private SpriteSheet sheildSprite;
    private BufferedImage sheild;
    private int animation;
    
    
    public Armor(IPlayer player) {
        super(player);
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            sheild = loader.loadImage("/img/Sheild.png");
        } catch (IOException ex) {
            Logger.getLogger(Armor.class.getName()).log(Level.SEVERE, null, ex);
        }
        sheildSprite = new SpriteSheet(sheild);
        animation = 1;
    }
    
    public void tick(){
        
        animation++;
        if(animation==11)
            animation = 1;
        
        sheild = sheildSprite.cropImage(animation, 1, 24, 24);
        
        tempPlayer.tick();

    }
    
    @Override
    public void render(Graphics g){
        tempPlayer.render(g);
        g.drawImage(sheild, 1, 1, null);
    }
    
}
