
package Model;

import View.Game;
import View.SpriteSheet;
import java.util.Random;

public abstract class Monster extends Movable{
    
    private Random rand = new Random();
    private int count = 0;

    public Monster(int x, int y, Game game) {
        super(x, y, game);
 
        direction = rand.nextInt(4)+1;
    }
    
    @Override
    public void tick(){
        
        while(!canMove()){
            direction = rand.nextInt(4)+1;
        }
   
        if ((direction == 1)) {
            //up
            y -= dy;
        } else if ((direction == 2)) {
            //left
            x -= dx;
        } else if ((direction == 3)) {
            //down
            y += dy;
        } else if ((direction == 4)) {
            //right
            x += dx;
        }
        
        if(x<=0)
            direction = 4;
        
        if(y<=0)
            direction = 3;
        
        if(x>=game.getWidth()-tileSize)
            direction = 2;
        
        if(y>=game.getHeight()-tileSize)
            direction = 1;
        
        super.tick();
    }
    
    
    
    

 
    
}
