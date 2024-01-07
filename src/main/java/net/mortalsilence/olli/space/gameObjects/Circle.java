package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import processing.core.PApplet;

public class Circle extends GameObject{
    @Override
    public void render(AsteroidsApplet applet){
        applet.circle(50,50,50);
    }
}
