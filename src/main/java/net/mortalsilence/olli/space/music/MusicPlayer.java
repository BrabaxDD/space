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
    private String currentWorkingDirectory = AsteroidsApplet.asteroidsApplet.sketchPath("src"+ File.separator+"main"+File.separator+"java"+File.separator+"net"+File.separator+"mortalsilence"+File.separator+"olli"+File.separator+"space"+File.separator+"music");
    public MusicPlayer(float startVolume, String relativeFolderPath){
        this.setVolume(startVolume);
        this.folderPath =  currentWorkingDirectory + File.separator + relativeFolderPath;
    }
    public void update() {
        if(!this.onOff){
            AsteroidsApplet.asteroidsApplet.getBackgroundPlayer().setVolume(0);
        }
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
        return soundFiles;
    }
}