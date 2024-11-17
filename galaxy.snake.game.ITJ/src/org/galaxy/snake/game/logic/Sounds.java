package org.galaxy.snake.game.logic;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Sounds
{
    private Clip music;
    private Clip explosion;
    private Clip boost;

    public Sounds()
    {
        backgroundMusic("src/Sounds/Wav/background_music.wav");
        explosionSound("src/Sounds/Wav/explosion_sound.wav");
        boostSound("src/Sounds/Wav/boost_sound.wav");
    }

    public void backgroundMusic(String filePath)
    {
        try 
        {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            music = AudioSystem.getClip();
            music.open(audioStream);
        } 
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.println("Audio unavailable");
        }
    }
    public void explosionSound(String filePath)
    {
        try 
        {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            explosion = AudioSystem.getClip();
            explosion.open(audioStream);

        } 
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.println("Audio unavailable");
        }
    }

    public void boostSound(String filePath)
    {
        try
        {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            boost = AudioSystem.getClip();
            boost.open(audioStream);

        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            System.out.println("Audio unavailable");
        }
    }

    public void playLoop() {
        if (music != null && !music.isRunning())
        {
            music.loop(Clip.LOOP_CONTINUOUSLY);
            music.start();
        }
    }

    public void stopLoop() {
        if (music != null && music.isRunning())
        {
            music.setFramePosition(0);
            music.stop();
            music.flush();
        }
    }

    public void closeLoop()
    {
        if (music != null)
        {
            music.flush();
            music.close();
        }
    }

    public void playExplosion()
    {
        if (explosion != null)
        {
            explosion.stop();
            explosion.setFramePosition(0);
            explosion.start(); 
        }
    }

    public void closeExplosion()
    {
        if (explosion != null)
        {
            explosion.flush();
            explosion.close();
        }
    }

    public void playBoost()
    {
        if (boost != null)
        {
            boost.setFramePosition(0);
            boost.start();
        }
    }

    public void closeBoost()
    {
        if (boost != null)
        {
            boost.flush();
            boost.close();
        }
    }

}
