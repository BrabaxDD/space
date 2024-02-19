package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PApplet;
import processing.core.PVector;

public class GameObject {
    protected Scene scene;
    protected PVector pos;
    public GameObject(Scene scene){
        this.scene = scene;
    }
    public void render(){}
    public void process() {}
    public PVector getPos(){return pos;}
}
