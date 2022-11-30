 package game.java.assetManagement;

 import javax.sound.sampled.AudioInputStream;
 import javax.sound.sampled.AudioSystem;
 import javax.sound.sampled.Clip;
 import java.io.File;

 public class AudioPlayer {

     /**
      * Plays the input audio file when called in a permanent loop. Suitable for background music.
      * Terminates on application closure
      * @param filepath the filepath of the audio file
      */
     public void playMusic(String filepath){
         try {
             File musicPath = new File(filepath);
             if(musicPath.exists()){
                 AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                 Clip clip = AudioSystem.getClip();
                 clip.open(audioInput);
                 clip.start();
                 clip.loop(Clip.LOOP_CONTINUOUSLY);
             }
             else{
                 System.out.println("Can't find file");
             }
         }
         catch (Exception ex){
             ex.printStackTrace();
         }
     }

     /**
      * Plays the input audio file when called. Suitable for sound effects when click through the menu.
      * @param filepath the filepath of the audio file
      */
     public void playClickSound(String filepath){
         try {
             File musicPath = new File(filepath);
             if(musicPath.exists()){
                 AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                 Clip clip = AudioSystem.getClip();
                 clip.open(audioInput);
                 clip.start();
             }
             else{
                 System.out.println("Can't find file");
             }
         }
         catch (Exception ex){
             //prints out the error message
             ex.printStackTrace();
         }
     }
 }
