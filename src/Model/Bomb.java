
package Model;

import Controller.Controller;
import View.Game;
import View.Sound;
import View.SpriteSheet;
import java.awt.Graphics;

public abstract class Bomb extends Collectable{
    
    BombState currentState;
    
    BombState explodedBomb;
    BombState safeBomb;
    
    public Bomb(int x, int y, Game game) {
        super(x, y, game);
        
        ss = game.getBombSprite();
        image = ss.cropImage(1, 1, tileSize, tileSize);
        
        explodedBomb = new ExplodedBomb(x, y, game, this);
        safeBomb = new SafeBomb(x, y, game, this);
        
        currentState = safeBomb;
    }
    
    @Override
    public void tick(){
        currentState.tick();
    }
    
    @Override
    public void render(Graphics g){
        currentState.render(g);
    }
    
    @Override
    public void collected(){
        currentState.bombHit(this);
    }
    
    public void setState(BombState state){
        currentState = state;
    }
    
    public BombState getState(){
        return currentState;
    }
    
    public BombState getExplodedBombState(){
        return explodedBomb;
    }
    
    public BombState getSafeBombState(){
        return safeBomb;
    }
    
}
