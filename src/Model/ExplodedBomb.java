package Model;

import Controller.Controller;
import View.Game;
import View.Sound;
import java.awt.Graphics;

public class ExplodedBomb extends Collectable implements BombState {
    
    Bomb bomb;

    public ExplodedBomb(int x, int y, Game game, Bomb bomb) {
        super(x, y, game);
        this.bomb = bomb;
        
        ss = game.getBombSprite();
        image = ss.cropImage(1, 1, tileSize, tileSize);
        step = 1;
        maxStep = 16;
    }

    @Override
    public void tick() {
        
        if (step == 1) {
            sound = game.getExplosionSound();
            sound.play();
        }
        
        step++;
        if (step == maxStep) {
            //game.getPainSound().play();
            Controller controller = game.getController();
            controller.removeCollectable(this);
        } 
        else {
            image = ss.cropImage(step, 1, tileSize, tileSize);
        }
    }
    
    public void render(Graphics g){
        super.render(g);
    }

    @Override
    public void bombHit(Bomb B) {
        
    }

}
