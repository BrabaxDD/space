package net.mortalsilence.olli.space.events;

import java.util.ArrayList;

public class Eventbus {
    private ArrayList<MouseLeftClickListener> MouseLeftClickListeners = new ArrayList<>();
    private ArrayList<ButtonPressedListener> ButtonPressedListeners = new ArrayList<>();
    public void registerEventMouseLeftClick (MouseLeftClickListener listener){
        this.MouseLeftClickListeners.add(listener);
    }
    public void registerEventButtonPressed(ButtonPressedListener listener){
        this.ButtonPressedListeners.add(listener);
    }
    public void MouseLeftClick(int x,int y){
        for(MouseLeftClickListener listener: this.MouseLeftClickListeners){
            listener.mouseLeftClick(x,y);
        }
    }
    public void ButtonPressed(){
        for(ButtonPressedListener listener: this.ButtonPressedListeners){
            listener.buttonPressed();
        }
    }


}
