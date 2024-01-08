package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PApplet;

public class GameObject {
    protected Scene scene;
    public GameObject(Scene scene){
        this.scene = scene;
    }
    public void render(){}
    public void process() {}
}
