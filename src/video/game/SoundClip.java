package video.game;

/**
 *
 * @author diana.barrios 
 * @author NissimBetesh
 *
 */

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.IOException;
import java.net.URL;

public class SoundClip {
    private AudioInputStream sample;
    private Clip clip;
    private boolean looping = false;
    private int repeat = 0;
    private String filename = "";

    /**
     * Constructor default
     */
    public SoundClip() {
        try {
                //crea el Buffer de sonido
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {}
    }
    
    /**
     * Constructor that loads sound file
     * @param filename <code>String</code> name of file
     */
    public SoundClip(String filename) {
            //Llama al constructor default.
        this();
        load(filename);
    }
    
    /**
     * 
     * @return <code>Clip</code> object with the sound
     */
    public Clip getClip() { 
        return clip; 
    }
    
    /**
     * set looping sound file
     * @param looping <code>boolean</code> value to set loop
     */
    public void setLooping(boolean looping) {
        this.looping = looping; 
    }

    /**
     * know if sound is being looped
     * @return <code>boolean</code> value of looped file
     */
    public boolean getLooping() {
        return looping;
    }

    /**
     * define number of repeats
     * @param repeat <code>int</code> number of repetitions
     */
    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }
    /**
     * get repetitions
     * @return <code>int</code> value of repetitions
     */
    public int getRepeat() { 
        return repeat; 
    }
    
    /**
     * Assign name to file
     * @param filename <code>String</code> name of file
     */
    public void setFilename(String filename) { 
        this.filename = filename; 
    }

    /**
     * get the name of the file
     * @return <code>String</code> name of file
     */
    public String getFilename() { 
        return filename;
    }

    /**
     * verify if the file is loaded
     * @return <code>boolean</code> whether file is loaded
     */
    public boolean isLoaded() {
        return (boolean)(sample != null);
    }

    /**
     * function that gets the url of the sound file
     * @param filename <code>String</code> name of the file
     * @return <code>URL</code> object of the url
     */
    private URL getURL(String filename) {
        URL url = null;
        try {
            url = this.getClass().getResource(filename);
        }
        catch (Exception e) { 
            System.out.println("" + filename + "does not exist");
        }
        return url;
    }

    /**
     * load the audio file
     * @param audiofile <code>String</code> name of the file
     * @return <code>boolean</code> value of success of load
     */
    public boolean load(String audiofile) {
        try {
            setFilename(audiofile);
            sample = AudioSystem.getAudioInputStream(getURL(filename));
            clip.open(sample);
            return true;

        } catch (IOException e) {
            return false;
        } catch (UnsupportedAudioFileException e) {
            return false;
        } catch (LineUnavailableException e) {
            return false;
        }
    }

    /**
     * play the sound
     */
    public void play() {
            //se sale si el sonido no a sido cargado
        if (!isLoaded()) {
            return;
        }
            //vuelve a empezar el sound clip
        clip.setFramePosition(0);

            //Reproduce el sonido con repeticion opcional.
        if (looping)
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        else
            clip.loop(repeat);
    }

    /**
     * Stop sound play
     */
    public void stop() {
        clip.stop();
    }

}

