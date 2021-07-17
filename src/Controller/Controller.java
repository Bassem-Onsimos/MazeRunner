
package Controller;

import Model.Bullet;
import Model.Collectable;
import Model.Monster;
import Model.Tile;
import View.Game;
import java.awt.Graphics;
import java.util.ArrayList;

public class Controller {
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Monster> monsters = new ArrayList<>();
    private ArrayList<Collectable> collectables = new ArrayList<>();
    private Tile[][] tiles;
    
    private int width = 0;
    private int height = 0;
    
    private Game game;
    
    public Controller(Game game){
        this.game = game;
    }
    
    public void tick(){
        for(int i=0; i<bullets.size(); i++){
            if((bullets.get(i).getX()>game.getWidth()) || (bullets.get(i).getX()<0) || (bullets.get(i).getY()>game.getHeight()) || (bullets.get(i).getY()<0)){
                removeBullet(bullets.get(i));
                continue;
            }
            bullets.get(i).tick();
        }
        
        for(int i=0; i<monsters.size(); i++){
            monsters.get(i).tick();
        }
        
        for(int i=0; i<collectables.size(); i++){
            collectables.get(i).tick();
        }
    }
    
    public void render(Graphics g){

        for(int i=0; i<collectables.size(); i++){
            collectables.get(i).render(g);
        }
        for(int i=0; i<bullets.size(); i++){
            bullets.get(i).render(g);
        }
        
        for(int i=0; i<monsters.size(); i++){
            monsters.get(i).render(g);
        }
        
        for(int x=0; x<width; x++){
            for(int y=0; y<height; y++){
                if(tiles[x][y]!=null)
                    tiles[x][y].render(g);
            }
        }
    }
    
    public void addBullet(Bullet b) {
        bullets.add(b);
    }
    
    public void removeBullet(Bullet b){
        bullets.remove(b);
        b = null;
    }
    
    public void addMonster(Monster m){
        monsters.add(m);
    }
    
    public void removeMonster(Monster m){
        monsters.remove(m);
    }
    
    public void addCollectable(Collectable c){
        collectables.add(c);
    }
    
    public void removeCollectable(Collectable c){
        collectables.remove(c);
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }
    
    public ArrayList<Collectable> getCollectables() {
        return collectables;
    }
    
    public void setTiles(Tile[][] tiles){
        this.tiles = tiles;
    }
    
    public Tile[][] getTiles(){
        return tiles;
    }
    
    public void setHeight(int height){
        this.height = height;
    }
    
    public void setWidth(int width){
        this.width = width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getWidth(){
        return width;
    }
    
    public void removeTile(int x, int y){
        tiles[x][y] = null;
    }
    
}
