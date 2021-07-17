
package View;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostGameState implements GameState {

    Game game;
    BufferedImage menu;
    Sound sound;
    
    public PostGameState(Game game){
        this.game = game;
    }
    
    @Override
    public void initialize() {
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            if(game.gameWon==true){
                menu = loader.loadImage("/img/youWin.png");
                sound = new Sound("/sound/tada.wav");
                sound.play();
            }
            else{
                menu = loader.loadImage("/img/GameOver.png");
                sound = new Sound("/sound/gameover.wav");
                sound.play();
            }
        } catch (IOException ex) {
            Logger.getLogger(PauseState.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        //g.drawImage(background, 0,0, Width, Height, this);
        game.background.render(g);
        game.player.render(g);
        game.controller.render(g);
        g.drawImage(menu, 0, 0, null);
        ///////////////////////////////////
        g.dispose();
        bs.show();
    }
    
}
