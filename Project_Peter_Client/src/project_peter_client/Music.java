/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_peter_client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import sun.audio.*;
import java.io.*;

/**
 *
 * @author Faishal
 */
public class Music {
    InputStream music;
    AudioStream audios;
    AudioData data;
    ContinuousAudioDataStream loop=null;
    
    public void login()
    {
        try {
            music=new FileInputStream(new File("src\\music\\login.wav"));
            audios=new AudioStream(music);
            AudioPlayer.player.start(audios); 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void menu()
    {
        try {
            music=new FileInputStream(new File("src\\music\\menuUtama.wav"));
            audios=new AudioStream(music);
            AudioPlayer.player.start(audios); 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void game()
    {
        try {
            music=new FileInputStream(new File("src\\music\\playMenu.wav"));
            audios=new AudioStream(music);
            AudioPlayer.player.start(audios); 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void room()
    {
        try {
            music=new FileInputStream(new File("src\\music\\roomGame.wav"));
            audios=new AudioStream(music);
            AudioPlayer.player.start(audios); 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void stop(){
        AudioPlayer.player.stop(audios); 
    }
}
