package Controller;

import Model.Bullet;
import Model.EntityFactory;
import Model.Player;
import View.Game;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Game game;
    EntityFactory factory;

    public KeyInput(Game game) {
        this.game = game;
        factory = game.getFactory();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(game.getCurrentState()==game.getPlayingState() || game.getCurrentState()==game.getLoadState()){
            Player player = game.getPlayer();
            if (key == KeyEvent.VK_UP) {
                player.setDirection(1);
                player.setDy(2);
                player.setMoving(true);
            } else if (key == KeyEvent.VK_DOWN) {
                player.setDirection(3);
                player.setDy(2);
                player.setMoving(true);
            } else if (key == KeyEvent.VK_RIGHT) {
                player.setDirection(4);
                player.setDx(2);
                player.setMoving(true);
            } else if (key == KeyEvent.VK_LEFT) {
                player.setDirection(2);
                player.setDx(2);
                player.setMoving(true);
            }

            if (key == KeyEvent.VK_SPACE) {
                PlayerData data = game.getData();
                if(data.getBullets()>0){
                    Controller controller = game.getController();
                    Bullet bullet = (Bullet)factory.createEntity("Bullet", (int) game.getPlayer().getX(), (int) game.getPlayer().getY(), game);
                    bullet.setDirection(game.getPlayer().getDirection());
                    controller.addBullet(bullet);
                    data.setBullets(data.getBullets() - 1);
                }
                else{
                    game.getEmptyGunSound().play();
                }
            }
            
            if(key == KeyEvent.VK_S){
                Save save = new Save(game);
            }
        }

        if (key == KeyEvent.VK_ESCAPE) {
            if (game.getCurrentState() == game.getPlayingState() || game.getCurrentState() == game.getLoadState()) {
                game.setState(game.getPauseState());
            } else if (game.getCurrentState() == game.getPauseState()) {
                game.setState(game.getPlayingState());
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        
        if(game.getCurrentState()==game.getPlayingState() || game.getCurrentState()==game.getLoadState()){
            Player player = game.getPlayer();
            if (key == KeyEvent.VK_UP) {
                //player.setDy(0);
                player.setMoving(false);
            } else if (key == KeyEvent.VK_DOWN) {
                //player.setDy(0);
                player.setMoving(false);
            } else if (key == KeyEvent.VK_RIGHT) {
                //player.setDx(0);
                player.setMoving(false);
            } else if (key == KeyEvent.VK_LEFT) {
                //player.setDx(0);
                player.setMoving(false);
            }
        }
    }

}
