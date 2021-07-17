
package Model;

import View.Game;
import java.awt.Rectangle;

public abstract class Movable extends Entity {
    
    protected int dx, dy;
    protected int step = 1;
    protected int maxStep = 0;
    protected int direction = 4;
    protected int prevDirection1 = 0;
    protected int prevDirection2 = 0;
    protected int prevDirection3 = 0;

    protected boolean moving = false;

    public Movable(int x, int y, Game game) {
        super(x, y, game);
    }
    
    public void tick(){
        step++;
        if(step==maxStep)
            step = 1;
        
        image = ss.cropImage(step, direction, tileSize, tileSize);
    }
    
    public boolean canMove(){
        int nextX = 0, nextY = 0;
        
        if(this instanceof Bullet){
            nextX = x;
            nextY = y;
        }
        else{
            if(direction==1){ //up
                nextX = x;
                nextY = y-dy;
            }else if(direction==2){ //left 
                nextX = x-dx;
                nextY = y;
            }else if(direction==3){ //down
                nextX = x;
                nextY = y+dy;
            }else if(direction==4){ //right
                nextX = x+dx;
                nextY = y;
            }
        }
        
        Rectangle bounds = new Rectangle(nextX, nextY, width, height);
        Tile[][] tiles = game.getTiles();
        
        for(int x=0; x<tiles.length; x++){
            for(int y=0; y<tiles[0].length; y++){
                if(tiles[x][y]!=null && bounds.intersects(tiles[x][y])){
                    if(!(this instanceof Bullet) || (this instanceof Bullet && tiles[x][y] instanceof RockTile))
                        return false;
                }
            }
        }

        return true;
    }
    
    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        prevDirection3 = prevDirection2;
        prevDirection2 = prevDirection1;
        prevDirection1 = this.direction;
        this.direction = direction;
    }
    
    public void setMoving(boolean moving){
        this.moving = moving;
    }
    
    public boolean isMoving(){
        return !(dx==0 && dy==0);
    }
    
    
            
    
    
    
}
