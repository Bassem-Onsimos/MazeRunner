
package Model;

import View.Game;
import View.Sound;
import View.SpriteSheet;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity extends Rectangle {
    protected Game game;
    protected final int tileSize = 22;
    
    protected BufferedImage image;
    protected SpriteSheet ss;
    protected Sound sound;
    
    public Entity(int x, int y, Game game){
        setBounds(x, y, tileSize, tileSize);
        this.game = game;
    }
    
    public abstract void tick();
    
    public void render(Graphics g){
        g.drawImage(image, x, y, null);
    }
    
    @Override
    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
