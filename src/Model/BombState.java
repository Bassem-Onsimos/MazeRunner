
package Model;

import java.awt.Graphics;

public interface BombState {
    
    public void tick();
    
    public void render(Graphics g);
    
    public void bombHit(Bomb B);
    
}
