package net.mortalsilence.olli.space.events;

import net.mortalsilence.olli.space.gameObjects.Asteroid;
import net.mortalsilence.olli.space.gameObjects.Projektile;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Eventbus {
    private ArrayList<MouseLeftClickListener> mouseLeftClickListeners = new ArrayList<>();
    private ArrayList<ButtonPressedListener> buttonPressedListeners = new ArrayList<>();

    private ArrayList<ProjektileMovedListener> projektileMovedListeners = new ArrayList<>();
    private ArrayList<AsteroidMovedListener> asteroidMovedListeners = new ArrayList<>();

    private  ArrayList<SpaceshipProjektileHitListener> spaceshipProjektileHitListeners = new ArrayList<>();

    public void registerEventMouseLeftClick (MouseLeftClickListener listener){
        this.mouseLeftClickListeners.add(listener);
    }


    //Modell Listener

    private ArrayList<SpaceshipProjektileMovedListener> spaceshipProjektileMovedListenersToDelete = new ArrayList<>();
    private ArrayList<SpaceshipProjektileMovedListener> spaceshipProjektileMovedListenersToAdd = new ArrayList<>();

    private ArrayList<SpaceshipProjektileMovedListener> spaceshipProjektileMovedListeners = new ArrayList<>();
    public void registerSpaceshipProjektileMovedListener(SpaceshipProjektileMovedListener listener){
        spaceshipProjektileMovedListenersToAdd.add(listener);
    }
    public void deleteSpaceshipProjektileMovedListener(SpaceshipProjektileMovedListener listener){
        spaceshipProjektileMovedListenersToDelete.add(listener);
    }




    //modell Listener




    public void registerEventButtonPressed(ButtonPressedListener listener){
        this.buttonPressedListeners.add(listener);
    }

    public void registerAsteroidMovedListener(AsteroidMovedListener asteroidMovedListener){
        this.asteroidMovedListeners.add(asteroidMovedListener);
    }


    public void registerEventProjektileMoved(ProjektileMovedListener listener){
        this.projektileMovedListeners.add(listener);
    }

    public void registerSpaceshipProjektileHitListener(SpaceshipProjektileHitListener listener){
        this.spaceshipProjektileHitListenersToAdd.add(listener);
        PApplet.println("Projektil Hinzugefügt");
    }

    public void MouseLeftClick(int x,int y){
        for(MouseLeftClickListener listener: this.mouseLeftClickListeners){
            listener.mouseLeftClick(x,y);
        }
    }
    public void ButtonPressed(){
        for(ButtonPressedListener listener: this.buttonPressedListeners){
            listener.buttonPressed();
        }
    }
    public void ProjektileMoved(int x, int y, Projektile projektile){
        for(ProjektileMovedListener projektileMovedListener:this.projektileMovedListeners){
            projektileMovedListener.ProjektileMoved(x,y,projektile);
        }
    }
    public void astroidMoved(Asteroid asteroid){
        for(AsteroidMovedListener asteroidMovedListener: this.asteroidMovedListeners){
            asteroidMovedListener.astroidMoved(asteroid);
        }
    }
    public void spaceshipProjektileMoved(PVector pos, Projektile projektile){
        for(SpaceshipProjektileMovedListener spaceshipProjektileMovedListener: this.spaceshipProjektileMovedListeners){
            spaceshipProjektileMovedListener.spaceshipProjektileMoved(pos, projektile);
        }
    }

    public void spaceshipProjektileHit(int exp) {
        for(SpaceshipProjektileHitListener spaceshipProjektileHitListener: this.spaceshipProjektileHitListeners) {
            spaceshipProjektileHitListener.spaceshipProjektileHit(exp);
        }
    }

    private ArrayList<SpaceshipProjektileHitListener> spaceshipProjektileHitListenersToDelete = new ArrayList<>();
    private ArrayList<SpaceshipProjektileHitListener> spaceshipProjektileHitListenersToAdd = new ArrayList<>();

    public void deleteSpaceshipHitListeners(SpaceshipProjektileHitListener listener){
        spaceshipProjektileHitListenersToDelete.add(listener);
        PApplet.println("Projektil deleted");
    }




    //listen werden am ende der Szenen geupdated um Bugs zu vermeiden

    public void cleanup(){
        for(SpaceshipProjektileMovedListener spaceshipProjektileMovedListener:spaceshipProjektileMovedListenersToDelete){
            this.spaceshipProjektileMovedListeners.remove(spaceshipProjektileMovedListener);
        }
        spaceshipProjektileMovedListenersToDelete = new ArrayList<>();

        this.spaceshipProjektileMovedListeners.addAll(spaceshipProjektileMovedListenersToAdd);

        this.spaceshipProjektileMovedListenersToAdd  = new ArrayList<>();

        this.spaceshipProjektileHitListeners.addAll(spaceshipProjektileHitListenersToAdd);

        this.spaceshipProjektileHitListenersToAdd  = new ArrayList<>();

        for(SpaceshipProjektileHitListener spaceshipProjektileHitListener:spaceshipProjektileHitListenersToDelete){
            this.spaceshipProjektileHitListeners.remove(spaceshipProjektileHitListener);
            PApplet.println("Projektil wirklich deleted");
        }
        spaceshipProjektileHitListenersToDelete = new ArrayList<>();
    }



}
