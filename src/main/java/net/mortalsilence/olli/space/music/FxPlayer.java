package net.mortalsilence.olli.space.music;

import net.mortalsilence.olli.space.AsteroidsApplet;
import processing.core.PApplet;
import processing.sound.SoundFile;

import java.io.File;
import java.util.ArrayList;

public class FxPlayer extends MusicPlayer {
    private ArrayList<SoundFile> soundFiles;

    int playing = 0;
    public FxPlayer(float startVolume) {
        super(startVolume, "Fx");
        PApplet.println("Volume fxPlayer: "+volume);
        this.soundFiles = loadSoundFiles();
        PApplet.println(folderPath);
        PApplet.println(soundFiles.size());
    }

    public void playSound(int indexSound){
        if(playing < 10){
            soundFiles.get(indexSound).play();
            soundFiles.get(indexSound).amp(volume);
        }
    }

    @Override
    public void setVolume(float volume){
        String voluume = PApplet.str(volume*100);
        AsteroidsApplet.asteroidsApplet.getWriterLine().writeToLine(AsteroidsApplet.ADRESS_TO_SPACE+"options.txt", 2, voluume );
        this.volume = volume;
    }

    public int getPlaying(){return playing;}

    public ArrayList<SoundFile> getSoundFiles(){return soundFiles;}

    public void update(){
        playing = 0;
        for(SoundFile file : soundFiles){
            if(file.isPlaying()){
                playing++;
            }
        }
    }

}
