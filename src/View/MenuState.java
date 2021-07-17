
package View;

import Controller.MouseInput;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuState implements GameState {

    private Game game;
    private BufferedImage background = null;
            
    public MenuState(Game game){
        this.game = game;
    }
    
    @Override
    public void initialize() {
        game.Width = 720;
        game.Height = 509;
        
        game.setPreferredSize(new Dimension(game.Width, game.Height));
        game.setMaximumSize(new Dimension(game.Width, game.Height));
        game.setMinimumSize(new Dimension(game.Width, game.Height));
        
        game.frame.remove(game.panel);
        game.frame.pack();
        
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            background = loader.loadImage("/img/StartMenu.png");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        game.addMouseListener(new MouseInput(game));

    }

    @Override
    public void tick() {
    }

    @Override
    public void render() {
        BufferStrategy bs = game.getBufferStrategy();
        if(bs == null){
            game.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        ///////////////////////////////////
        g.drawImage(background, 0, 0, null);

        ///////////////////////////////////
        g.dispose();
        bs.show();
    }
    
}
