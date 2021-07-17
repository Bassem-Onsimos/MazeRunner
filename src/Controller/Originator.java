
package Controller;

public class Originator {
        
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public Memento saveToMemento() {
        return new Memento(score);
    }
    
    public void getScoreFromMemento(Memento memento){
        score = memento.getScore();
    }
}
