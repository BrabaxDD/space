package net.mortalsilence.olli.space;

import net.mortalsilence.olli.space.gameObjects.Button;
import net.mortalsilence.olli.space.gameObjects.Circle;
import net.mortalsilence.olli.space.gameObjects.SceneSwitcherButton;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PApplet;

import java.util.ArrayList;

public class AsteroidsApplet extends PApplet {
    public static AsteroidsApplet asteroidsApplet;
    private boolean mousePressedPreviousFrame;
    private Scene mainmenu;
    private  Scene activeScene;

    public static void main(String[] args) {
        PApplet.main("net.mortalsilence.olli.space.AsteroidsApplet");
    }

    @Override
    public void settings() {
        this.size(500,500);
        this.mainmenu = new Scene(new ArrayList<>());
        this.activeScene = this.mainmenu;
        this.mainmenu.addObject(new Circle());
        Button b = new Button();
        this.activeScene.getEventbus().registerEventMouseLeftClick(b);
        this.mainmenu.addObject(b);
        AsteroidsApplet.asteroidsApplet = this;
    }

    @Override
    public void draw(){
        this.background(0);
        //Inputs
        if(this.mousePressed == true&& mousePressedPreviousFrame == false){
            this.activeScene.getEventbus().MouseLeftClick(this.mouseX,this.mouseY);
        }

        this.activeScene.process();
        this.activeScene.render();
        this.mousePressedPreviousFrame = mousePressed;
    }

    public void switchScene (Scene scene){
        this.activeScene = scene;
    }

}
