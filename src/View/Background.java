
package View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Background {
    
    private Game game;
    private int tileSize =22;
    private BufferedImage image;
    private BufferedImageLoader loader = new BufferedImageLoader();
    
    public Background(BufferedImage image, Game game){
        this.game = game;
        this.image = image;
    }
    
    public void render(Graphics g){
        for(int i=0; i<game.getWidth(); i+=tileSize){
            for(int j=0; j<game.getHeight(); j+=tileSize){
                g.drawImage(image, i, j, null);
            }
        }
    }
    
}
