
package View;

import Controller.Controller;
import Controller.KeyInput;
import Controller.Level;
import Controller.MazeGenerator;
import Controller.NewLevel;
import Controller.PlayerData;
import Model.EntityFactory;
import Model.Player;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class PlayingState implements GameState {
    
    private Game game;
    
    public PlayingState(Game game){
        this.game = game;
    }
    
    @Override
    public void initialize() {
        
        game.setPreferredSize(new Dimension(game.Width, game.Height));
        game.setMaximumSize(new Dimension(game.Width, game.Height));
        game.setMinimumSize(new Dimension(game.Width, game.Height));
        
        game.frame.add(game.panel, BorderLayout.NORTH);
        game.frame.pack();
        
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
