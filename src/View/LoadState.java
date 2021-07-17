
package View;

import Controller.Controller;
import Controller.KeyInput;
import Controller.Load;
import Model.EntityFactory;
import Model.Player;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class LoadState implements GameState {
    
    protected Game game;
    
    public LoadState(Game game){
        this.game = game;
    }
    
    @Override
    public void initialize() {
        
        game.resources = new Resources();
        
        game.factory = new EntityFactory();
        
        game.controller = new Controller(game);
        
        Load load = new Load(game);
        
        game.setPreferredSize(new Dimension(game.Width, game.Height));
        game.setMaximumSize(new Dimension(game.Width, game.Height));
        game.setMinimumSize(new Dimension(game.Width, game.Height));
        
        game.frame.add(game.panel, BorderLayout.NORTH);
        game.frame.pack();
        
        game.background = new Background(game.resources.getBackground(), game);
        
        game.resources.getBackgroundMusic().loop(0);
        
        game.player = (Player) game.factory.createEntity("Player", game.playerX, game.playerY, game);  
        if(game.playerArmourState)
            game.player.setArmoured(true);
        
        game.addKeyListener(new KeyInput(game));

    }

    @Override
    public void tick() {
        game.player.tick();
        game.controller.tick();
        game.timer.tick();
        
        game.data.setSeconds(game.timer.getSeconds());
        game.data.setMinutes(game.timer.getMinutes());
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
        game.background.render(g);
        game.player.render(g);
        game.controller.render(g);
        ///////////////////////////////////
        g.dispose();
        bs.show();
    }


    
    
    
}
