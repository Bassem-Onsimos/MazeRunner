
package Controller;

import View.Game;
import View.InfoPanel;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class InitializeGame {
    
    private static JFrame frame;
    
    public static void main(String args[]){
        
        frame = new JFrame("Maze Runner");
        
        PlayerData playerData = new PlayerData();
        InfoPanel panel = new InfoPanel(playerData);
        Game game = new Game(playerData, panel, frame);

        frame.add(game, BorderLayout.SOUTH);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLocation(290, 0);
        frame.setVisible(true);
        
        game.start();
    }
    
}
