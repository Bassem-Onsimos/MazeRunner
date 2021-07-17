
package Controller;

import View.Observer;
import java.util.ArrayList;

public class PlayerData implements Subject {

    private ArrayList<Observer> observers;
    
    private int score = 0;
    private int health = 100;
    private int lives = 3;
    private int bullets = 6;
    private int minutes = 0;
    private int seconds = 0;
    
    private Originator originator = new Originator();
    private CareTaker careTaker = new CareTaker();
    
    public PlayerData(){
        observers = new ArrayList<>();
    }
    
    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unRegister(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observers){
            observer.update(score, lives, health, bullets, minutes, seconds);
        }
    }

    public void setScore(int score) {
        this.score = score;
        originator.setScore(score);
        careTaker.add(originator.saveToMemento());
        notifyObservers();
    }

    public void setHealth(int health) {
        this.health = health;
        notifyObservers();
    }

    public void setLives(int lives) {
        this.lives = lives;
        notifyObservers();
    }

    public int getScore() {
        return originator.getScore();
    }

    public int getHealth() {
        return health;
    }

    public int getLives() {
        return lives;
    }
    
    public void setBullets(int bullets) {
        this.bullets = bullets;
        notifyObservers();
    }
    
    public int getBullets() {
        return bullets;
    }
    
     public void setMinutes(int minutes) {
        this.minutes = minutes;
        notifyObservers();
    }
    
    public int getMinutes() {
        return minutes;
    }
    
     public void setSeconds(int seconds) {
        this.seconds = seconds;
        notifyObservers();
    }
    
    public int getSeconds() {
        return seconds;
    }
    
    
}
