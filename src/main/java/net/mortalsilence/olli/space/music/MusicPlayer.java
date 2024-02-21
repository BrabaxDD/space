package net.mortalsilence.olli.space.music;

import net.mortalsilence.olli.space.AsteroidsApplet;
import processing.core.PApplet;
import processing.sound.SoundFile;

import java.io.File;
import java.util.ArrayList;

public class MusicPlayer {
    protected float volume;
    String folderPath;

    boolean onOff;
    private String currentWorkingDirectory = AsteroidsApplet.asteroidsApplet.sketchPath(AsteroidsApplet.ADRESS_TO_SPACE +"music");
    public MusicPlayer(float startVolume, String relativeFolderPath){
        this.setVolume(startVolume);
        this.folderPath =  currentWorkingDirectory + File.separator + relativeFolderPath;
    }
    public void update() {

    }


    protected boolean isAudioFile(String fileName) {
        String[] audioExtensions = {".wav", ".mp3", ".ogg", ".flac"}; // Füge bei Bedarf weitere hinzu
        for (String extension : audioExtensions) {
            if (fileName.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
    public void setVolume(float volume){}

    protected ArrayList<SoundFile> loadSoundFiles(){
        // Initialisiere die ArrayList für SoundFile-Objekte
        ArrayList<SoundFile> soundFiles = new ArrayList<>();

        // Lade alle Sound-Dateien im Ordner
        File folder = new File(folderPath);
        //PApplet.println(folder);
        //PApplet.println(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            assert files != null;
            for (File file : files) {

                if (file.isFile() && isAudioFile(file.getName())) {
                    // Füge SoundFile-Objekt zur ArrayList hinzu
                    System.out.println(file);
                    SoundFile soundFile = new SoundFile(AsteroidsApplet.asteroidsApplet, file.getPath());
                    PApplet.println(file.getName());
                    soundFiles.add(soundFile);
                }
            }
        }
        return soundFiles;
    }
}
