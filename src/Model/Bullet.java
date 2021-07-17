package Model;

import Controller.Controller;
import View.Game;

public class Bullet extends Allies {

    public Bullet(int x, int y, Game game) {
        super(x, y, game);

        maxStep = 5;
        ss = game.getBulletSprite();

        game.getBulletSound().play();
    }

    @Override
    public void tick() {

        if (direction == 1) { //up
            y -= dy;
        } else if (direction == 2) { //left
            x -= dx;
        } else if (direction == 3) { //down
            y += dy;
        } else if (direction == 4) { //right
            x += dx;
        }

        step++;
        if (step == maxStep) {
            step = 1;
        }

        image = ss.cropImage(step, direction, tileSize, tileSize);

        int monster = hitMonster();

        if (monster != -1) {
            Controller controller = game.getController();
            controller.removeMonster(controller.getMonsters().get(monster));
            controller.removeBullet(this);
        }

        collectItems();
        hitTile();

        if (!canMove()) {
            Controller controller = game.getController();
            controller.removeBullet(this);
        }

    }

    public void hitTile() {
        Controller controller = game.getController();
        for (int i = 0; i < controller.getWidth(); i++) {
            for (int j = 0; j < controller.getHeight(); j++) {
                Tile tile = controller.getTiles()[i][j];
                if (tile != null && (getBounds().intersects(tile.getBounds())) && (tile instanceof WoodenTile)) {
                    controller.removeTile(i, j);
                    controller.removeBullet(this);
                }
            }
        }
    }

    @Override
    public void setDirection(int direction) {
        super.setDirection(direction);

        if (direction == 1) { //up
            dy = 8;
        } else if (direction == 2) { //left
            dx = 8;
        } else if (direction == 3) { //down
            dy = 8;
        } else if (direction == 4) { //right
            dx = 8;
        }
    }

}
