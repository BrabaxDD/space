package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import processing.core.PApplet;

public class Circle extends GameObject{
    @Override
    public void render(){
        AsteroidsApplet.asteroidsApplet.circle(50,50,50);
    }
}
