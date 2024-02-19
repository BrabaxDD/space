package net.mortalsilence.olli.space.music;

import net.mortalsilence.olli.space.AsteroidsApplet;
import processing.core.PApplet;
import processing.sound.SoundFile;

import java.util.ArrayList;

public class FxPlayer extends MusicPlayer {
    private ArrayList<SoundFile> soundFiles;
    public FxPlayer(float startVolume) {
        super(startVolume, "Fx");
        PApplet.println("Volume fxPlayer: "+volume);
        this.soundFiles = loadSoundFiles();
        PApplet.println(folderPath);
        PApplet.println(soundFiles.size());
    }

    public void playSound(int indexSound){
        soundFiles.get(indexSound).play();
        soundFiles.get(indexSound).amp(volume);
    }

    @Override
    public void setVolume(float volume){
        String voluume = PApplet.str(volume*100);
        AsteroidsApplet.asteroidsApplet.getWriterLine().writeToLine(AsteroidsApplet.ADRESS_TO_SPACE+"options.txt", 2, voluume );
        this.volume = volume;
    }

}
