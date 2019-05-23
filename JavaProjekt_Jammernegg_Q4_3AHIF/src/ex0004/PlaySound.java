/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex0004;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author floha
 */
public class PlaySound implements Runnable
{

  @Override
  public void run()
  {
   // playSound();
  }
  
  // <editor-fold desc="Sound"> 
  public void playSound()   
  {
    
      try
      {
        AudioInputStream audioInputStream =
           AudioSystem.getAudioInputStream(
             new File(
               "/neu/cartoon001.wav").getAbsoluteFile());

        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
      }
      catch(Exception ex)
      {
        System.out.println("Error with playing sound.");
        ex.printStackTrace();
      }
    }
  //</editor-fold>
}
