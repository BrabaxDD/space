package net.mortalsilence.olli.space.scenes;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.Eventbus;
import net.mortalsilence.olli.space.gameObjects.GameObject;
import net.mortalsilence.olli.space.utility.SweepAndPrune;
import processing.core.PApplet;

import java.util.ArrayList;

public class Scene {
    private ArrayList<GameObject> toDelete = new ArrayList<>();

    private ArrayList<GameObject> toAdd = new ArrayList<>();

    private Eventbus eventbus;
    private ArrayList<GameObject> gameObjects;

    private SweepAndPrune sap;

    public Scene(ArrayList<GameObject> objects, SweepAndPrune sap){
        this.eventbus = new Eventbus();
        this.gameObjects = objects;
        this.sap = sap;
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
        this.sap.removeObject(object);
        PApplet.println("Debug: Object has been Deleted");
    }

    public void addObject (GameObject object){
        this.toAdd.add(object);
        this.sap.addObject(object);
        System.out.println("objekt zu sap hinzugeügt: " + object + " Objekt Pos: " + object.getPos());
    }
    public Eventbus getEventbus() {
        return eventbus;
    }

    public int getObjects(){
        return gameObjects.size();
    }

    public ArrayList<GameObject> getGameObjects(){return this.gameObjects;}

    public void setEventbus(Eventbus eventbus) {
        this.eventbus = eventbus;
    }
}
