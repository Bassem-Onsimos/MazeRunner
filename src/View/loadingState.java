
package View;

import Controller.Controller;
import Controller.KeyInput;
import Controller.Level;
import Controller.PlayerData;
import Model.EntityFactory;
import Model.Player;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class loadingState implements GameState{
    
    private Game game;
    private BufferedImage image;
    private BufferedImage background = null;

    private SpriteSheet ss;
    
    private int x=1, y=1;
    
    private boolean gameLoaded = false;
    
    public loadingState(Game game) {
        this.game = game;
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            background = loader.loadImage("/img/black.png");
            ss = new SpriteSheet(loader.loadImage("/img/loading.png"));
            image = ss.cropImage(1, 1, 85, 60);
        } catch (IOException e) {
            e.printStackTrace();
        }       
    }

    @Override
    public void initialize() {
        loadGame();
    }
    
    public void loadGame(){
        game.resources = new Resources();
        
        game.factory = new EntityFactory();
        
        game.controller = new Controller(game);
        game.level = new Level("/img/finalMaze.png", game);
        
        game.controller.setTiles(game.level.getTiles());
        game.controller.setHeight(game.level.getHeight());
        game.controller.setWidth(game.level.getWidth());
        
        game.background = new Background(game.resources.getBackground(), game);
        
        game.resources.getBackgroundMusic().loop(0);
        
        game.player = (Player) game.factory.createEntity("Player", game.playerX, game.playerY, game);
        
        game.addKeyListener(new KeyInput(game));

        gameLoaded = true;
    }

    @Override
    public void tick() {
        
        if(gameLoaded)
            game.setState(game.getPlayingState());
        
        
        x++;
        
        if(x==3){
            x=1;
            y++;
        }
        
        if(y==7)
            y=1;
        
        try {
            Thread.sleep(5);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        image = ss.cropImage(x, y, 85, 60);
        
        
        /*
        x++;
        if(x==9)
            x=1;
        
        
        
        image = ss.cropImage(x, 1, 102, 102);
        */
        
        
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
        g.drawImage(image, 300, 200, null);
        ///////////////////////////////////
        g.dispose();
        bs.show();
    }
    
    
    
}
