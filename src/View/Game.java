
package View;

import Controller.Timer;
import Controller.Controller;
import Controller.Level;
import Controller.PlayerData;
import Model.EntityFactory;
import Model.Player;
import Model.Tile;
import java.awt.Canvas;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
    
    GameState currentState;
    
    GameState playingState;
    GameState pauseState;
    GameState menuState;
    GameState postGameState;
    GameState loadState;
    GameState loadingState;
    
    protected Timer timer = new Timer();
    
    protected boolean gameWon = false;
    
    protected int Width;
    protected int Height;
    protected static final String title = "Maze Runner";
    
    protected boolean running = false;
    protected Thread thread;
    
    protected PlayerData data;
    
    protected EntityFactory factory;

    protected Level level;
    protected Player player;
    protected int playerX, playerY;
    protected boolean playerArmourState = false;

    
    protected Background background = null;
    protected Resources resources = null;
    
    protected Controller controller;
    
    protected InfoPanel panel;
    
    protected JFrame frame;
    
    public Game(PlayerData data, InfoPanel panel, JFrame frame){
        this.frame = frame;
        
        playingState = new PlayingState(this);
        pauseState = new PauseState(this);
        menuState = new MenuState(this);
        postGameState = new PostGameState(this);
        loadState = new LoadState(this);
        loadingState = new loadingState(this);
        
        currentState = menuState;
        
        this.data = data;
        this.panel = panel;
        
        currentState.initialize();
        
    }
        
    public void init(){

    }
    
    public synchronized void start(){
        if(running)
            return;
        
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    private synchronized void stop(){
        if(!running)
            return;
        
        running = false;
        
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.exit(1);
    }
    
    @Override
    public void run() {
        requestFocus();
        init();
        long initialTime = System.nanoTime();
        final double rate = 60.0;
        double ns = 1000000000 / rate;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
       
        while(running){
            long now = System.nanoTime();
            delta += (now - initialTime) / ns;
            initialTime = now;
            if(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                System.out.println(updates);
                timer += 1000;
                updates = 0;
                frames = 0;
            }
            
        }
        stop();
    }
    
    private void tick(){
        currentState.tick();
    }
    
    private void render(){
        currentState.render();
    }
    
    
    public SpriteSheet getCharacterSprite(){
        return resources.getCharacterSprite();
    }
    
    public SpriteSheet getBulletSprite(){
        return this.resources.getBulletSprite();
    }
    
    public BufferedImage getRockTileSprite(){
        return this.resources.getRockTile();
    }
    
    public BufferedImage getWoodenTileSprite(){
        return this.resources.getWoodenTile();
    }
    
    public SpriteSheet getMonsterASprite(){
        return this.resources.getMonsterASprite();
    }
    
    public SpriteSheet getMonsterBSprite(){
        return this.resources.getMonsterBSprite();
    }
    
    public SpriteSheet getBombSprite(){
        return this.resources.getBombSprite();
    }
    
    public BufferedImage getGiftASprite(){
        return this.resources.getGiftA();
    }
    
    public BufferedImage getGiftBSprite(){
        return this.resources.getGiftB();
    }
    
    public SpriteSheet getCoinSprite(){
        return this.resources.getCoinSprite();
    }
    
    public SpriteSheet getSheildSprite(){
        return this.resources.getSheildSprite();
    }
    
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public void setWidth(int Width) {
        this.Width = Width;
    }

    public void setHeight(int Height) {
        this.Height = Height;
    }
  
    public Tile[][] getTiles() {
        return controller.getTiles();
    }

    public PlayerData getData() {
        return data;
    }

    public void setData(PlayerData data) {
        this.data = data;
    }

    public EntityFactory getFactory() {
        return factory;
    }

    public Sound getBackgroundMusic() {
        return resources.getBackgroundMusic();
    }

    public Sound getBulletSound() {
        return resources.getBulletSound();
    }

    public Sound getCoinSound() {
        return resources.getCoinSound();
    }

    public Sound getEmptyGunSound() {
        return resources.getEmptyGunSound();
    }

    public Sound getExplosionSound() {
        return resources.getExplosionSound();
    }

    public Sound getFootStepsSound() {
        return resources.getFootStepsSound();
    }

    public Sound getPainSound() {
        return resources.getPainSound();
    }

    public Sound getRefillWeaponSound() {
        return resources.getRefillWeaponSound();
    }

    public Sound getGiftSound() {
        return resources.getGiftSound();
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public GameState getPlayingState() {
        return playingState;
    }

    public GameState getPauseState() {
        return pauseState;
    }

    public GameState getMenuState() {
        return menuState;
    }

    public GameState getPostGameState() {
        return postGameState;
    }
    
    public GameState getLoadState() {
        return loadState;
    }
    
    public GameState getLoadingState() {
        return loadingState;
    }

    public void setState(GameState state) {
        if((currentState==loadingState || currentState==menuState) && (state==playingState || state==loadState)){
            state.initialize();
        }
        
        if(state==pauseState)
            timer.pause();
        
        if(state==playingState || state==loadState)
            timer.start();
        
        this.currentState = state;
        
        if(state == postGameState || state == loadingState)
            state.initialize();
    }
    
    public boolean getPlayerArmourState() {
        return playerArmourState;
    }

    public void setPlayerArmourState(boolean playerArmourState) {
        this.playerArmourState = playerArmourState;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }
    
    public void setTime(int minutes, int seconds){
        this.timer.setMinutes(minutes);
        this.timer.setSeconds(seconds);
    }
    
}
