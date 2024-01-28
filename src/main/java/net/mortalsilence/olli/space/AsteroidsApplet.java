package net.mortalsilence.olli.space;

import net.mortalsilence.olli.space.factorys.GameSceneFactory;
import net.mortalsilence.olli.space.gameObjects.Button;
import net.mortalsilence.olli.space.gameObjects.SceneSwitcherButton;
import net.mortalsilence.olli.space.gameObjects.Spawner;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;

import java.util.ArrayList;

public class AsteroidsApplet extends PApplet {
    public static AsteroidsApplet asteroidsApplet;
    private boolean mousePressedPreviousFrame;

    //private Scene mainmenu; Ist jetzt auf Slot 1 der GameScene Factory
    private  Scene activeScene;

    private final boolean debugModeOn = false;
    //PFont myFont = createFont("Comic Sans MS Fett", 32);

    public static void main(String[] args) {
        PApplet.main("net.mortalsilence.olli.space.AsteroidsApplet");
    }

    @Override
    public void settings() {
        this.fullScreen(1);
        AsteroidsApplet.asteroidsApplet = this;
        this.activeScene = GameSceneFactory.buildGameScene(1);
        this.activeScene.render();
        println(this.activeScene.getObjects());
        //this.mainmenu.addObject(b);
    }

    @Override
    public void draw(){
        this.background(0);
        //Inputs
        if(this.mousePressed == true&& mousePressedPreviousFrame == false){
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
}
