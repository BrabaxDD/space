package net.mortalsilence.olli.space.music;

import net.mortalsilence.olli.space.AsteroidsApplet;

import java.io.File;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PSketch;
import processing.sound.*;

public class BackgroundPlayer extends MusicPlayer {
    private ArrayList<SoundFile> soundFiles;

    private int currentlyPlaying;

    private float volume;

    public BackgroundPlayer() {
        super(0, "Sounds");
        PApplet.println("Volume background Player: "+volume);
        this.soundFiles = loadSoundFiles();
        PApplet.println(folderPath);
        PApplet.println(soundFiles.size());
        // Beispiel: Spiele alle Sound-Dateien ab
        soundFiles.get(0).amp(0);
        soundFiles.get(0).play();
        currentlyPlaying = 0;
    }

    @Override
    public void update() {
        soundFiles.get(currentlyPlaying).amp(volume);
        if (!soundFiles.get(currentlyPlaying).isPlaying()) {
            if (currentlyPlaying == soundFiles.size()-1) {
                currentlyPlaying = 0;
            } else currentlyPlaying++;

            soundFiles.get(currentlyPlaying).play();
        }
    }


    @Override
    public void setVolume(float volume){
        this.volume = volume;
    }
}
