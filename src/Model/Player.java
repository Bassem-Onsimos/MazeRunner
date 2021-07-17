
package Model;

import Controller.PlayerData;
import View.Game;
import View.Sound;
import View.SpriteSheet;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Allies implements IPlayer {
    
    private Sound painSound;
    private boolean armoured = false; 
    private BufferedImage sheild;
    private SpriteSheet sheildSprite;
    private int animation;
    
    public Player(int x, int y, Game game){
        super(x, y, game);
        
        maxStep = 10;
        
        dx = 0;
        dy = 0;
        
        ss = game.getCharacterSprite();
        image = ss.cropImage(step, direction, tileSize, tileSize);
        
        sheildSprite = game.getSheildSprite();
        sheild = sheildSprite.cropImage(step, direction, tileSize, tileSize);
        
        sound = game.getFootStepsSound();
        painSound = game.getPainSound();
    }
    
    @Override
    public void tick(){
        
        if (moving) {
            if ((direction == 1) && canMove()) {
                //up
                y -= dy;
            } else if ((direction == 2) && canMove()) {
                //left
                x -= dx;
            } else if ((direction == 3) && canMove()) {
                //down
                y += dy;
            } else if ((direction == 4) && canMove()) {
                //right
                x += dx;
            }

            if (!sound.isRunning()) {
                sound.loop(30000);
            }
        } else {
            center();
            if (dx == 0 && dy == 0) {
                sound.stop();
            }
        }

        if (x <= 0) {
            x = 0;
        }

        if (y <= 0) {
            y = 0;
        }
        
        /*
        if (x >= game.getWidth()) {
            x = game.getWidth() - tileSize;
        }
        */

        if (y >= game.getHeight() - tileSize) {
            y = game.getHeight() - tileSize;
        }

        if ((x == game.getWidth())) {
            //Winning Condition
            game.setGameWon(true);
            sound.stop();
            game.setState(game.getPostGameState());
        }

        if (!(dx == 0 && dy == 0)) {
            super.tick();
        }
        
        if(!armoured){
            int monster = hitMonster();
            if(monster!=-1){
                if(!colliding){    
                    painSound.play();
                    PlayerData data = game.getData();
                    data.setHealth(data.getHealth()-20);
                    if(data.getHealth()==0){
                        data.setLives(data.getLives()-1);
                        data.setHealth(100);
                        if(data.getLives()==0){
                            game.setGameWon(false);
                            sound.stop();
                            game.setState(game.getPostGameState());
                            //End Game
                        }
                    }
                    colliding = true;
                }
            }
            else
                colliding = false;
        }
        else{
            animation++;
            if(animation==10)
                animation = 1;

            sheild = sheildSprite.cropImage(animation, direction, tileSize, tileSize);
        }
        collectItems();
        
    }
    
    
    public void center() {
        int maxX;
        int minX;
        int maxY;
        int minY;

        if (x % tileSize != 0) {
            minX = x;
            maxX = x;

            while (minX % tileSize != 0) {
                minX--;
            }

            while (maxX % tileSize != 0) {
                maxX++;
            }

            if (direction == 4) {
                //right
                if (x < maxX) {
                    x++;
                }
            } else if (direction == 2) {
                //left
                if (x > minX) {
                    x--;
                }
            } else if ((prevDirection1 == 4) || (prevDirection2 == 4) || (prevDirection3 == 4)) {
                //previous right
                if (x < maxX) {
                    x++;
                }
            } else if ((prevDirection1 == 2) || (prevDirection2 == 2) || (prevDirection3 == 2)) {
                //left
                if (x > minX) {
                    x--;
                }
            }
        } else {
            dx = 0;
        }

        if (y % tileSize != 0) {
            minY = y;
            maxY = y;

            while (minY % tileSize != 0) {
                minY--;
            }

            while (maxY % tileSize != 0) {
                maxY++;
            }

            if (direction == 1) {
                //up
                if (y > minY) {
                    y--;
                }
            } else if (direction == 3) {
                //down
                if (y < maxY) {
                    y++;
                }
            } else if ((prevDirection1 == 1) || (prevDirection2 == 1) || (prevDirection3 == 1)) {
                //previous up
                if (y > minY) {
                    y--;
                }
            } else if ((prevDirection1 == 3) || (prevDirection2 == 3) || (prevDirection3 == 3)) {
                //previous down
                if (y < maxY) {
                    y++;
                }
            }
        } else {
            dy = 0;
        }

    }

    public void setArmoured(boolean armoured){
        this.armoured = armoured;
    }
    
    public void render(Graphics g){
        super.render(g);
        if(armoured){
            g.drawImage(sheild, x, y, null);
        }
    }

    public boolean isArmoured() {
        return armoured;
    }
    
    
    
    
}
