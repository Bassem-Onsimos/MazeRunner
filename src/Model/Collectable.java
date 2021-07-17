
package Model;

import Controller.Controller;
import View.Game;

public abstract class Collectable extends RockTile {
    
    protected int step;
    protected int maxStep;
        
    public Collectable(int x, int y, Game game) {
        super(x, y, game);
    }
    
    public void collected(){
        Controller controller = game.getController();
        controller.removeCollectable(this);
    }
    
}
