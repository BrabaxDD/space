package net.mortalsilence.olli.space.events;

import net.mortalsilence.olli.space.gameObjects.Asteroid;
import processing.core.PVector;

import java.util.ArrayList;

public class Eventbus {
    private ArrayList<MouseLeftClickListener> mouseLeftClickListeners = new ArrayList<>();
    private ArrayList<ButtonPressedListener> buttonPressedListeners = new ArrayList<>();

    private ArrayList<ProjektileMovedListener> projektileMovedListeners = new ArrayList<>();
    private ArrayList<AsteroidMovedListener> asteroidMovedListeners = new ArrayList<>();

    private ArrayList<SpaceshipProjektileMovedListener> spaceshipProjektileMovedListeners = new ArrayList<>();
    public void registerEventMouseLeftClick (MouseLeftClickListener listener){
        this.mouseLeftClickListeners.add(listener);
    }

    public void registerSpaceshipProjektileMovedListener(SpaceshipProjektileMovedListener listener){
        spaceshipProjektileMovedListeners.add(listener);
    }
    public void deleteSpaceshipProjektileMovedListener(SpaceshipProjektileMovedListener listener){
        spaceshipProjektileMovedListeners.remove(listener);
    }

    public void registerEventButtonPressed(ButtonPressedListener listener){
        this.buttonPressedListeners.add(listener);
    }

    public void registerAsteroidMovedListener(AsteroidMovedListener asteroidMovedListener){
        this.asteroidMovedListeners.add(asteroidMovedListener);
    }


    public void registerEventProjektileMoved(ProjektileMovedListener listener){
        this.projektileMovedListeners.add(listener);
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
    public void ProjektileMoved(int x,int y){
        for(ProjektileMovedListener projektileMovedListener:this.projektileMovedListeners){
            projektileMovedListener.ProjektileMoved(x,y);
        }
    }
    public void astroidMoved(Asteroid asteroid){
        for(AsteroidMovedListener asteroidMovedListener: this.asteroidMovedListeners){
            asteroidMovedListener.astroidMoved(asteroid);
        }
    }
    public void spaceshipProjektileMoved(PVector pos){
        for(SpaceshipProjektileMovedListener spaceshipProjektileMovedListener: this.spaceshipProjektileMovedListeners){
            spaceshipProjektileMovedListener.spaceshipProjektileMoved(pos);
        }
    }



}
