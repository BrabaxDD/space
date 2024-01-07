package net.mortalsilence.olli.space.events;

import java.util.ArrayList;

public class Eventbus {
    private ArrayList<MouseLeftClickListener> MouseLeftClickListeners = new ArrayList<>();
    public void registerEventMouseLeftClick (MouseLeftClickListener listener){
        this.MouseLeftClickListeners.add(listener);
    }
    public void MouseLeftClick(int x,int y){
        for(MouseLeftClickListener listener: this.MouseLeftClickListeners){
            listener.mouseLeftClick(x,y);
        }
    }
}
