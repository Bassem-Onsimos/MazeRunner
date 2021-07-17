
package Controller;

public class Timer{
    
    private boolean running;
    private int milliseconds = 0;
    private int seconds = 0;
    private int minutes = 0;
    private int hours  = 0;
    
    public void start(){
        this.running = true;
    }
    
    public void pause(){
        this.running = false;
    }
    
    public void tick(){
        if(running){
            if(milliseconds>60){
                milliseconds=0;
                seconds++;
            }
            if(seconds>60){
                seconds=0;
                minutes++;
            }
            if(minutes>60){
                minutes=0;
                hours++;
            }
            
            milliseconds++;
        }
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
  
}
