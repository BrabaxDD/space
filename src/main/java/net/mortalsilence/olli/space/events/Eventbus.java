package net.mortalsilence.olli.space.events;

import java.util.ArrayList;

public class Eventbus {
    private ArrayList<MouseLeftClickListener> mouseLeftClickListeners = new ArrayList<>();
    private ArrayList<ButtonPressedListener> buttonPressedListeners = new ArrayList<>();

    private ArrayList<ProjektileMovedListener> projektileMovedListeners = new ArrayList<>();
    public void registerEventMouseLeftClick (MouseLeftClickListener listener){
        this.mouseLeftClickListeners.add(listener);
    }
    public void registerEventButtonPressed(ButtonPressedListener listener){
        this.buttonPressedListeners.add(listener);
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


}
