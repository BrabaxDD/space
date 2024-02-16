package net.mortalsilence.olli.space;

import net.mortalsilence.olli.space.factorys.GameSceneFactory;
import net.mortalsilence.olli.space.gameObjects.Button;
import net.mortalsilence.olli.space.gameObjects.SceneSwitcherButton;
import net.mortalsilence.olli.space.gameObjects.Spawner;
import net.mortalsilence.olli.space.music.BackgroundPlayer;
import net.mortalsilence.olli.space.music.FxPlayer;
import net.mortalsilence.olli.space.scenes.Scene;
import net.mortalsilence.olli.space.utility.WriterLine;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class AsteroidsApplet extends PApplet {
    public static AsteroidsApplet asteroidsApplet;
    public PrintWriter output;
    private boolean mousePressedPreviousFrame;

    private BackgroundPlayer backgroundPlayer ;

    //private Scene mainmenu; Ist jetzt auf Slot 1 der GameScene Factory
    private  Scene activeScene;

    private boolean debugModeOn;
    //PFont myFont = createFont("Comic Sans MS Fett", 32);

    public static void main(String[] args) {
        PApplet.main("net.mortalsilence.olli.space.AsteroidsApplet");
    }

    public String[] gameRules;

    private FxPlayer fxPlayer;

    private WriterLine writerLine;

    @Override
    public void settings() {
        this.fullScreen(1);
        AsteroidsApplet.asteroidsApplet = this;
        this.activeScene = GameSceneFactory.buildGameScene(1);
        this.activeScene.render();
        println(this.activeScene.getObjects());
        this.gameRules  = loadStrings("src/main/java/net/mortalsilence/olli/space/gameRules.txt");
        println(gameRules[1]);
        this.debugModeOn = this.getGameRuleBoolean(3);
        this.backgroundPlayer = new BackgroundPlayer();
        this.backgroundPlayer.setVolume(0);
        this.fxPlayer = new FxPlayer(2);
        String[] options = loadStrings("src/main/java/net/mortalsilence/olli/space/options.txt");
        this.backgroundPlayer.setVolume(Float.parseFloat(options[0]));
        PApplet.println("Volume loaded background: "+Float.parseFloat(options[0]));
        this.fxPlayer.setVolume(Float.parseFloat(options[1]));
        PApplet.println("Volume loaded fx: "+Float.parseFloat(options[1]));
        this.writerLine = new WriterLine();

    }

    @Override
    public void draw(){
        this.background(0);
        //Inputs
        if(this.mousePressed && !mousePressedPreviousFrame){
            this.activeScene.getEventbus().MouseLeftClick(this.mouseX,this.mouseY);
        }
        if(this.keyPressed){
            this.activeScene.getEventbus().ButtonPressed();
        }

        this.activeScene.process();
        this.activeScene.render();
        this.mousePressedPreviousFrame = mousePressed;
        if(debugModeOn){
            fill(250,130,145);
            AsteroidsApplet.asteroidsApplet.text( "Number of entities:"+this.activeScene.getObjects(),250,250);
            AsteroidsApplet.asteroidsApplet.text( "FPS: "+(int)this.frameRate,250,200);
        }
        this.backgroundPlayer.update();

    }

    public void switchScene (Scene scene){
        this.activeScene = scene;
    }

    public boolean isDebugModeOn(){
        return debugModeOn;
    }

    public Scene getActiveScene() {
        return activeScene;
    }

    public float getGameRule(int index) {
        String[] cache = gameRules[index].replaceAll("\\s+","").split(":");
        if(!Objects.equals(cache[1], "false") && !Objects.equals(cache[1], "true")){
            return Float.parseFloat(cache[1]);
        }else return 0;
    }

    public boolean getGameRuleBoolean(int index){
        String[] cache = gameRules[index].replaceAll("\\s+","").split(":");
        if(Objects.equals(cache[1], "false") || Objects.equals(cache[1], "true")){
            return Boolean.parseBoolean(cache[1]);
        }else return false;
    }

    public void setVolumeGlobal(float volumeGlobal){
        this.backgroundPlayer.setVolume(volumeGlobal);
    }

    public FxPlayer getFxPlayer(){return fxPlayer;}

    public BackgroundPlayer getBackgroundPlayer(){return  backgroundPlayer;}

    public WriterLine getWriterLine(){return this.writerLine;}


}
