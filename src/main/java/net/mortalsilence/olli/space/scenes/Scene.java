package net.mortalsilence.olli.space.scenes;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.Eventbus;
import net.mortalsilence.olli.space.gameObjects.GameObject;
import processing.core.PApplet;

import java.util.ArrayList;

public class Scene {
    private ArrayList<GameObject> toDelete = new ArrayList<>();

    private ArrayList<GameObject> toAdd = new ArrayList<>();

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


        if(this.toDelete != null){
            for(GameObject object : this.toDelete){
                this.gameObjects.remove(object);
            }
            this.toDelete = new ArrayList<>();
        }
        if(this.toAdd != null){
            this.gameObjects.addAll(this.toAdd);
            this.toAdd = new ArrayList<>();
        }
        this.eventbus.cleanup();

    }

    public void render(){
        for(GameObject object: this.gameObjects){
            object.render();
        }
    }

    public void deleteObject(GameObject object){
        toDelete.add(object);
        PApplet.println("Debug: Object has been Deleted");
    }

    public void addObject (GameObject object){
        this.toAdd.add(object);
    }
    public Eventbus getEventbus() {
        return eventbus;
    }

    public int getObjects(){
        return gameObjects.size();
    }

    public void setEventbus(Eventbus eventbus) {
        this.eventbus = eventbus;
    }
}
