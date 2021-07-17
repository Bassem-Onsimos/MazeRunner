
package View;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Resources {
    
    private BufferedImage background = null;
    private BufferedImage Character = null;
    private BufferedImage Bullet = null;
    private BufferedImage rockTile = null;
    private BufferedImage WoodenTile = null;
    private BufferedImage MonsterA = null;
    private BufferedImage MonsterB = null;
    private BufferedImage Bomb = null;
    private BufferedImage GiftA = null;
    private BufferedImage GiftB = null;
    private BufferedImage Coin = null;
    private BufferedImage Sheild = null;
    
    private SpriteSheet CharacterSprite = null;
    private SpriteSheet BulletSprite = null;
    private SpriteSheet MonsterASprite = null;
    private SpriteSheet MonsterBSprite = null;
    private SpriteSheet BombSprite = null;
    private SpriteSheet CoinSprite = null;
    private SpriteSheet SheildSprite = null;
    
    private final Sound backgroundMusic;
    private final Sound bulletSound;
    private final Sound coinSound;
    private final Sound emptyGunSound;
    private final Sound explosionSound;
    private final Sound footStepsSound;
    private final Sound painSound;
    private final  Sound refillWeaponSound;
    private final  Sound giftSound; 
    
    public Resources(){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            background = loader.loadImage("/img/rocks.png");
            Character = loader.loadImage("/img/CharacterSprite.png");
            Bullet = loader.loadImage("/img/BulletSprite.png");
            rockTile = loader.loadImage("/img/RockTile.png");
            WoodenTile = loader.loadImage("/img/WoodenTile.png");
            MonsterA = loader.loadImage("/img/MonsterA.png");
            MonsterB = loader.loadImage("/img/MonsterB.png");
            Bomb = loader.loadImage("/img/BombSprite.png");
            Coin = loader.loadImage("/img/coinSprite.png");
            GiftA = loader.loadImage("/img/SheildGift.png");
            GiftB = loader.loadImage("/img/gift.png");
            Sheild = loader.loadImage("/img/Sheild.png");
            
            CharacterSprite = new SpriteSheet(Character);
            BulletSprite = new SpriteSheet(Bullet);
            MonsterASprite = new SpriteSheet(MonsterA);
            MonsterBSprite = new SpriteSheet(MonsterB);
            BombSprite = new SpriteSheet(Bomb);
            CoinSprite = new SpriteSheet(Coin);
            SheildSprite = new SpriteSheet(Sheild);
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        bulletSound = new Sound("/sound/bullet.wav");
        coinSound = new Sound("/sound/coin.wav");
        emptyGunSound = new Sound("/sound/emptygun.wav");
        explosionSound = new Sound("/sound/explosion.wav");
        footStepsSound = new Sound("/sound/footsteps.wav");
        painSound = new Sound("/sound/pain.wav");
        refillWeaponSound = new Sound("/sound/refillWeapon.wav");
        giftSound = new Sound("/sound/gift.wav");
        backgroundMusic = new Sound("/sound/background.wav");
    
    }
    

    public BufferedImage getBackground() {
        return background;
    }

    public BufferedImage getCharacter() {
        return Character;
    }

    public BufferedImage getBullet() {
        return Bullet;
    }

    public BufferedImage getRockTile() {
        return rockTile;
    }
    
    public BufferedImage getWoodenTile() {
        return WoodenTile;
    }

    public BufferedImage getMonsterA() {
        return MonsterA;
    }

    public BufferedImage getMonsterB() {
        return MonsterB;
    }

    public BufferedImage getBomb() {
        return Bomb;
    }

    public BufferedImage getGiftA() {
        return GiftA;
    }

    public BufferedImage getGiftB() {
        return GiftB;
    }

    public BufferedImage getCoin() {
        return Coin;
    }

    public BufferedImage getSheild() {
        return Sheild;
    }

    public SpriteSheet getCharacterSprite() {
        return CharacterSprite;
    }

    public SpriteSheet getBulletSprite() {
        return BulletSprite;
    }

    public SpriteSheet getMonsterASprite() {
        return MonsterASprite;
    }

    public SpriteSheet getMonsterBSprite() {
        return MonsterBSprite;
    }

    public SpriteSheet getBombSprite() {
        return BombSprite;
    }

    public SpriteSheet getCoinSprite() {
        return CoinSprite;
    }

    public SpriteSheet getSheildSprite() {
        return SheildSprite;
    }

    public Sound getBackgroundMusic() {
        return backgroundMusic;
    }

    public Sound getBulletSound() {
        return bulletSound;
    }

    public Sound getCoinSound() {
        return coinSound;
    }

    public Sound getEmptyGunSound() {
        return emptyGunSound;
    }

    public Sound getExplosionSound() {
        return explosionSound;
    }

    public Sound getFootStepsSound() {
        return footStepsSound;
    }

    public Sound getPainSound() {
        return painSound;
    }

    public Sound getRefillWeaponSound() {
        return refillWeaponSound;
    }

    public Sound getGiftSound() {
        return giftSound;
    }
    
    
    
}
