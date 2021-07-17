
package View;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
            
    private Clip clip;
    
    public Sound(String path){
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(getClass().getResource(path));
            AudioFormat baseFormat = stream.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED, 
                    baseFormat.getSampleRate(), 
                    16, 
                    baseFormat.getChannels(), 
                    baseFormat.getChannels()*2,
                    baseFormat.getSampleRate()*10,
                    false
                    );
            
            AudioInputStream decodedStream = AudioSystem.getAudioInputStream(decodeFormat, stream);
            clip = AudioSystem.getClip();
            clip.open(decodedStream);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void play(){
        if(clip==null) return;
        stop();
        clip.setFramePosition(0);
        clip.start();
    }
    
    public void stop(){
        if(clip.isRunning())
            clip.stop();
    }
    
    public void loop(int n){
        clip.setFramePosition(n);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public boolean isRunning(){
        return clip.isRunning();
    }
    
}
