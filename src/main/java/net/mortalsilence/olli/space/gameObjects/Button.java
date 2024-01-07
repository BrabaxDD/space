package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.MouseLeftClickListener;

public class Button extends GameObject implements MouseLeftClickListener {
    @Override
    public void mouseLeftClick(int x, int y) {
        AsteroidsApplet.asteroidsApplet.circle(200,200,50);
        System.out.println("Button Pressed");
    }
}
