package net.mortalsilence.olli.space.scenes;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.gameObjects.GameObject;
import processing.core.PApplet;

import java.util.ArrayList;

public class Scene {
    private ArrayList<GameObject> gameObjects;
    private AsteroidsApplet applet;
    public Scene(ArrayList<GameObject> objects, AsteroidsApplet applet){
        this.applet = applet;
        this.gameObjects = objects;
    }
    public void process (){
        for(GameObject object: this.gameObjects){
            object.process();
        }
    }
    public void render(){
        for(GameObject object: this.gameObjects){
            object.render(this.applet);
        }
    }

    public void addObject (GameObject object){
        this.gameObjects.add(object);
    }
}
