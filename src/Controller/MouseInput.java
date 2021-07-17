package Controller;

import View.Game;
import View.Sound;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    Game game;
    Sound click;

    public MouseInput(Game game) {
        this.game = game;
        click = new Sound("/sound/click.wav");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (game.getCurrentState() == game.getMenuState()) {
            click.play();
            int x = e.getX();
            int y = e.getY();

            if (x > 420 && x < 681) {
                if (y > 87 && y < 151) {
                    //new game
                    game.setState(game.getLoadingState());
                } else if (y > 197 && y < 263) {
                    //load
                    game.setState(game.getLoadState());

                } else if (y > 304 && y < 367) {
                    //exit
                    System.exit(0);
                }
            }
        } else if (game.getCurrentState() == game.getPauseState()) {
            click.play();
            int x = e.getX();
            int y = e.getY();

            if (x > 200 && x < 480) {
                if (y > 180 && y < 255) {
                    //continue
                    game.setState(game.getPlayingState());
                } else if (y > 300 && y < 365) {
                    //save
                    Save save = new Save(game);

                } else if (y > 412 && y < 480) {
                    //exit
                    System.exit(0);
                }
            }
        } else if (game.getCurrentState() == game.getPostGameState()) {
            click.play();
            int x = e.getX();
            int y = e.getY();

            if (x > 200 && x < 480) {
                if (y > 300 && y < 365) {
                    //save
                    System.exit(0);

                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
