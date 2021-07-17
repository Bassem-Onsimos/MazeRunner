package Model;

import Controller.Controller;
import View.Game;

public abstract class Allies extends Movable {

    protected boolean colliding = false;

    public Allies(int x, int y, Game game) {
        super(x, y, game);
    }


    public int hitMonster() {
        for (int i = 0; i < game.getController().getMonsters().size(); i++) {
            if (getBounds().intersects(game.getController().getMonsters().get(i).getBounds())) {
                return i;
            }
        }
        return -1;
    }

    public void collectItems() {
        
        if(this instanceof Player){
            for (int i = 0; i < game.getController().getCollectables().size(); i++) {
                if (getBounds().intersects(game.getController().getCollectables().get(i).getBounds())) {
                    if(game.getController().getCollectables().get(i) instanceof Bomb)
                        game.getPainSound().play();
                    
                    game.getController().getCollectables().get(i).collected();
                }
            }
        }
        else if(this instanceof Bullet){
            Controller controller = game.getController();
            for (int i = 0; i < game.getController().getCollectables().size(); i++) {
                Collectable c = game.getController().getCollectables().get(i);
                if (getBounds().intersects(c.getBounds())) {
                    if(c instanceof Bomb){
                        ((Bomb) c).setState(((Bomb) c).explodedBomb);
                    }
                    else
                        controller.removeCollectable(c);
                    
                    controller.removeBullet((Bullet) this);
                }
                
            }
        }
        
    }

}
