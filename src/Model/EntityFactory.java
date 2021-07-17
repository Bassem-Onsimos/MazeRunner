
package Model;

import View.Game;

public class EntityFactory {
    Entity entity = null;
    
    public Entity createEntity(String type, int x, int y, Game game){
        
        switch (type){
            
            case "Player":
                entity = new Player(x, y, game);
                break;
                
            case "Bullet":
                entity = new Bullet(x, y, game);
                break;
        
            case "RockTile":
                entity = new RockTile(x, y, game);
                break;
                
            case "WoodenTile":
                entity = new WoodenTile(x, y, game);
                break;
                
            case "MonsterA":
                entity = new MonsterA(x, y, game);
                break;
        
            case "MonsterB":
                entity = new MonsterB(x, y, game);
                break;
                
            case "BombA":
                entity = new BombA(x, y, game);
                break;
            
            case "BombB":
                entity = new BombB(x, y, game);
                break;
                
            case "Coin":
                entity = new Coin(x, y, game);
                break;
                
            case "GiftA":
                entity = new GiftA(x, y, game);
                break;
            
            case "GiftB":
                entity = new GiftB(x, y, game);
                break;
                
        }
        
        return entity;

    }
    
}
