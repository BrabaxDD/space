package net.mortalsilence.olli.space.events;

import com.lowagie.text.DocWriter;
import net.mortalsilence.olli.space.gameObjects.*;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

import static processing.core.PApplet.println;

public class Eventbus {
    private ArrayList<MouseLeftClickListener> mouseLeftClickListeners = new ArrayList<>();
    private ArrayList<ButtonPressedListener> buttonPressedListeners = new ArrayList<>();

    private ArrayList<ProjektileMovedListener> projektileMovedListeners = new ArrayList<>();
    private ArrayList<AsteroidMovedListener> asteroidMovedListeners = new ArrayList<>();

    private ArrayList<PlayerLevelListener> playerLevelListeners = new ArrayList<>();

    private  ArrayList<SpaceshipProjektileHitListener> spaceshipProjektileHitListeners = new ArrayList<>();

    public void registerEventMouseLeftClick (MouseLeftClickListener listener){
        this.mouseLeftClickListeners.add(listener);
    }

    private  ArrayList<AlienUFOMovedListener> alienUFOMovedListeners = new ArrayList<>();


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

    private ArrayList<SpaceshipMovedListener> spaceshipMovedListeners = new ArrayList<>();
    private ArrayList<SpaceshipMovedListener> spaceshipMovedListenersToDelete = new ArrayList<>();
    private ArrayList<SpaceshipMovedListener> spaceshipMovedListenersToAdd = new ArrayList<>();
    public void registerSpaceshipMovedListener(SpaceshipMovedListener listener){
        spaceshipMovedListenersToAdd.add(listener);
    }
    public void deleteSpaceshipMovedListener(SpaceshipMovedListener listener){
        spaceshipMovedListenersToDelete.add(listener);
    }

    public void deleteAlienUFOMovedListeners(AlienUFOMovedListener listener){
        this.alienUFOMovedListeners.remove(listener);
    }



    //modell Listener




    public void registerEventButtonPressed(ButtonPressedListener listener){
        this.buttonPressedListeners.add(listener);
    }

    public void registerAlienUFOMovedListeners(AlienUFOMovedListener listener){
        this.alienUFOMovedListeners.add(listener);
    }

    public void registerAsteroidMovedListener(AsteroidMovedListener asteroidMovedListener){
        this.asteroidMovedListeners.add(asteroidMovedListener);
    }


    public void registerEventProjektileMoved(ProjektileMovedListener listener){
        this.projektileMovedListeners.add(listener);
    }

    public void registerSpaceshipProjektileHitListener(SpaceshipProjektileHitListener listener){
        this.spaceshipProjektileHitListenersToAdd.add(listener);
    }

    public void registerPlayerLevelListener(PlayerLevelListener listener){
        this.playerLevelListeners.add(listener);
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
    public void spaceshipProjektileMoved(PVector pos, Projektile projektile, GameObject shooter){
        for(SpaceshipProjektileMovedListener spaceshipProjektileMovedListener: this.spaceshipProjektileMovedListeners){
            spaceshipProjektileMovedListener.spaceshipProjektileMoved(pos, projektile, shooter);
        }
    }

    public void alienUFOMoved(AlienUFO ufo){
        for(AlienUFOMovedListener alienUFOMovedListener: this.alienUFOMovedListeners) {
            alienUFOMovedListener.alienUFOMoved(ufo);
        }
    }

    public void spaceshipProjektileHit(int exp) {
        for(SpaceshipProjektileHitListener spaceshipProjektileHitListener: this.spaceshipProjektileHitListeners) {
            spaceshipProjektileHitListener.spaceshipProjektileHit(exp);
        }
    }

    public void spaceshipMoved( PVector pos){
        for(SpaceshipMovedListener spaceshipMovedListener: this.spaceshipMovedListeners) {
            spaceshipMovedListener.spaceshipMoved(pos);
        }
    }

    public void playerLevelListen(int level, int xp){
        for(PlayerLevelListener listener : this.playerLevelListeners){
            listener.playerLevelListen(level, xp);
        }
    }

    private ArrayList<SpaceshipProjektileHitListener> spaceshipProjektileHitListenersToDelete = new ArrayList<>();
    private ArrayList<SpaceshipProjektileHitListener> spaceshipProjektileHitListenersToAdd = new ArrayList<>();

    public void deleteSpaceshipHitListeners(SpaceshipProjektileHitListener listener){
        spaceshipProjektileHitListenersToDelete.add(listener);
        println("Projektil deleted");
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
            println("Projektil wirklich deleted");
        }
        spaceshipProjektileHitListenersToDelete = new ArrayList<>();

        this.spaceshipMovedListeners.addAll(spaceshipMovedListenersToAdd);

        this.spaceshipMovedListenersToAdd  = new ArrayList<>();

        for(SpaceshipMovedListener spaceshipMovedListener:spaceshipMovedListenersToDelete){
            this.spaceshipMovedListeners.remove(spaceshipMovedListener);
            println("Projektil wirklich deleted");
        }
        spaceshipMovedListenersToDelete = new ArrayList<>();


    }



}
