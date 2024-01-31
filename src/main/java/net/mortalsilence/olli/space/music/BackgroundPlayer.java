package net.mortalsilence.olli.space.music;

import net.mortalsilence.olli.space.AsteroidsApplet;

import java.io.File;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PSketch;
import processing.sound.*;

public class BackgroundPlayer {
    private ArrayList<SoundFile> soundFiles;

    private int currentlyPlaying;

    private float volume;


    public BackgroundPlayer() {


        String currentWorkingDirectory = AsteroidsApplet.asteroidsApplet.sketchPath("src\\main\\java\\net\\mortalsilence\\olli\\space\\music");

        // Relativer Pfad zum Ordner "Sounds"
        String relativeFolderPath = "Sounds";

        // Kombiniere den aktuellen Pfad mit dem relativen Pfad
        String folderPath = currentWorkingDirectory + File.separator + relativeFolderPath;

        // Initialisiere die ArrayList für SoundFile-Objekte
        soundFiles = new ArrayList<SoundFile>();

        // Lade alle Sound-Dateien im Ordner
        File folder = new File(folderPath);
        PApplet.println(folder);
        PApplet.println(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            PApplet.println("Hey hier");
            File[] files = folder.listFiles();

            for (File file : files) {

                if (file.isFile() && isAudioFile(file.getName())) {
                    // Füge SoundFile-Objekt zur ArrayList hinzu
                    SoundFile soundFile = new SoundFile(AsteroidsApplet.asteroidsApplet, file.getPath());
                    PApplet.println(file.getName());
                    soundFiles.add(soundFile);
                }
            }
        }
        PApplet.println(soundFiles.size());
        // Beispiel: Spiele alle Sound-Dateien ab
        soundFiles.get(0).play();
        currentlyPlaying = 0;
    }

    public void update() {
        soundFiles.get(currentlyPlaying).amp(volume);
        if (!soundFiles.get(currentlyPlaying).isPlaying()) {
            if (currentlyPlaying == soundFiles.size()-1) {
                currentlyPlaying = 0;
            } else currentlyPlaying++;

            soundFiles.get(currentlyPlaying).play();
        }
    }

    private boolean isAudioFile(String fileName) {
        String[] audioExtensions = {".wav", ".mp3", ".ogg", ".flac"}; // Füge bei Bedarf weitere hinzu
        for (String extension : audioExtensions) {
            if (fileName.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    public void setVolume(float volume){
        this.volume = volume;
    }
}
