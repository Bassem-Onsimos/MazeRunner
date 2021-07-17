
package View;

import Controller.Subject;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel implements Observer {
    
    private int score = 0;
    private int lives = 3;
    private int health = 100;
    private int bullets = 6;
    private int minutes = 0;
    private int seconds = 0;
    
    private JLabel scoreTitle = new JLabel("Score");
    private JLabel healthTitle = new JLabel("Health");
    private JLabel livesTitle = new JLabel("Lives");
    private JLabel bulletsTitle = new JLabel("Bullets");
    private JLabel timeTitle = new JLabel("Time");
    
    private JLabel scoreLabel = new JLabel(Integer.toString(score));
    private JLabel livesLabel = new JLabel(Integer.toString(lives));
    private JLabel healthLabel = new JLabel(Integer.toString(health));
    private JLabel bulletsLabel = new JLabel(Integer.toString(bullets));
    private JLabel timeLabel = new JLabel(Integer.toString(minutes) + " : " + Integer.toString(seconds));
    
    private Subject playerData;
    
    public InfoPanel(Subject playerData){
        this.playerData = playerData;
        playerData.register(this);
        
        setLayout(new GridLayout(2, 4, 20, 5));
        add(scoreTitle);
        add(healthTitle);
        add(livesTitle);
        add(bulletsTitle);
        add(timeTitle);
        add(scoreLabel);
        add(healthLabel);
        add(livesLabel);
        add(bulletsLabel);
        add(timeLabel);
        
        scoreTitle.setForeground(Color.white);
        healthTitle.setForeground(Color.white);
        livesTitle.setForeground(Color.white);
        bulletsTitle.setForeground(Color.white);
        timeTitle.setForeground(Color.white);
        scoreLabel.setForeground(Color.white);
        healthLabel.setForeground(Color.white);
        livesLabel.setForeground(Color.white);
        bulletsLabel.setForeground(Color.white);
        timeLabel.setForeground(Color.white);
        
        Font font = new Font("Arial", Font.BOLD, 15);
        scoreTitle.setFont(font);
        healthTitle.setFont(font);
        livesTitle.setFont(font);
        bulletsTitle.setFont(font);
        timeTitle.setFont(font);
        scoreLabel.setFont(font);
        healthLabel.setFont(font);
        livesLabel.setFont(font);
        bulletsLabel.setFont(font);
        timeLabel.setFont(font);
        
        setBackground(Color.black);
    }

    @Override
    public void update(int score, int lives, int health, int bullets, int minutes, int seconds) {
        this.score = score;
        this.lives = lives;
        this.health = health;
        this.bullets = bullets;
        this.minutes = minutes;
        this.seconds = seconds;
        
        scoreLabel.setText(Integer.toString(score));
        healthLabel.setText(Integer.toString(health));
        livesLabel.setText(Integer.toString(lives));
        bulletsLabel.setText(Integer.toString(bullets));
        timeLabel.setText(Integer.toString(minutes) + " : " + Integer.toString(seconds));
    }
    
}
