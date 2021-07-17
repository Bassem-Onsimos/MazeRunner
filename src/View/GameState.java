
package View;

import java.awt.Graphics;

public interface GameState {
        
    public void initialize();
    
    public void tick();
    
    public void render();
    
}
