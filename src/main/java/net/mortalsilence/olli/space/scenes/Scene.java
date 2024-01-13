package net.mortalsilence.olli.space.scenes;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.Eventbus;
import net.mortalsilence.olli.space.gameObjects.GameObject;
import processing.core.PApplet;

import java.util.ArrayList;

public class Scene {


    private Eventbus eventbus;
    private ArrayList<GameObject> gameObjects;

    public Scene(ArrayList<GameObject> objects){
        this.eventbus = new Eventbus();
        this.gameObjects = objects;
    }
    public void process (){
        for(GameObject object: this.gameObjects){
            object.process();
        }
    }
    public void render(){
        for(GameObject object: this.gameObjects){
            object.render();
        }
    }

    public void deleteObject(GameObject object){this.gameObjects.remove(object);};

    public void addObject (GameObject object){
        this.gameObjects.add(object);
    }
    public Eventbus getEventbus() {
        return eventbus;
    }

    public void setEventbus(Eventbus eventbus) {
        this.eventbus = eventbus;
    }
}
