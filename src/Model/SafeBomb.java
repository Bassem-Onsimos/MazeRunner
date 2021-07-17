
package Model;

import Controller.PlayerData;
import View.Game;
import java.awt.Graphics;

public class SafeBomb extends Collectable implements BombState {
    
    Bomb bomb;
    
    public SafeBomb(int x, int y, Game game, Bomb bomb) {
        super(x, y, game);
        this.bomb = bomb;
        
        ss = game.getBombSprite();
        image = ss.cropImage(1, 1, tileSize, tileSize);

    }
    
    @Override
    public void tick(){
        
    }
    
    public void render(Graphics g){
        super.render(g);
    }

    @Override
    public void bombHit(Bomb B) {
        bomb.setState(bomb.getExplodedBombState());
        
        Player player = game.getPlayer();
        player.setArmoured(false);
        
        PlayerData data = game.getData();
        
        if(B instanceof BombA){
            data.setHealth(100);
            data.setLives(data.getLives() - 1);
            if (data.getLives() == 0) {
                System.out.println("Game Over");
                //End Game
            }
        }
        else if(B instanceof BombB){
            data.setBullets(0);
        }        
    }
    
}
