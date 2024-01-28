package net.mortalsilence.olli.space;

import net.mortalsilence.olli.space.gameObjects.Button;
import net.mortalsilence.olli.space.gameObjects.SceneSwitcherButton;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;

import java.util.ArrayList;

public class AsteroidsApplet extends PApplet {
    public static AsteroidsApplet asteroidsApplet;
    private boolean mousePressedPreviousFrame;
    private Scene mainmenu;
    private  Scene activeScene;

    private final boolean debugModeOn = false;
    //PFont myFont = createFont("Comic Sans MS Fett", 32);

    public static void main(String[] args) {
        PApplet.main("net.mortalsilence.olli.space.AsteroidsApplet");
    }

    @Override
    public void settings() {
        this.fullScreen(2);
        AsteroidsApplet.asteroidsApplet = this;
        this.mainmenu = new Scene(new ArrayList<>());
        this.activeScene = this.mainmenu;
        Button b = new SceneSwitcherButton(this.mainmenu,new PVector((AsteroidsApplet.asteroidsApplet.displayWidth/2)-300,(AsteroidsApplet.asteroidsApplet.displayHeight/2) -50),new PVector(600,100));
        this.activeScene.getEventbus().registerEventMouseLeftClick(b);
        this.mainmenu.addObject(b);
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
}
