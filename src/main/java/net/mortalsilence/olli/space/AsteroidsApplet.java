package net.mortalsilence.olli.space;

import net.mortalsilence.olli.space.gameObjects.Circle;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PApplet;

import java.util.ArrayList;

public class AsteroidsApplet extends PApplet {
    private Scene mainmenu;
    private  Scene activeScene;

    public static void main(String[] args) {
        PApplet.main("net.mortalsilence.olli.space.AsteroidsApplet");
    }

    @Override
    public void settings() {
        this.size(500,500);
        this.mainmenu = new Scene(new ArrayList<>(),this);
        this.activeScene = this.mainmenu;
        this.mainmenu.addObject(new Circle());
    }

    @Override
    public void draw(){
        this.activeScene.process();
        this.activeScene.render();
    }

    public void switchScene (Scene scene){
        this.activeScene = scene;
    }
}
