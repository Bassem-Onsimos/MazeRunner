
package Model;

import View.Game;
import java.awt.Graphics;

public abstract class PlayerDecorator implements IPlayer {
    protected IPlayer tempPlayer;
    
    public PlayerDecorator(IPlayer player){
        tempPlayer = player;        
    }
    
    public void tick(){
        tempPlayer.tick();
    }
    
    public void render(Graphics G){
        tempPlayer.render(G);
    }
}
