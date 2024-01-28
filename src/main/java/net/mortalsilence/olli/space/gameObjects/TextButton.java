package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PConstants;
import processing.core.PVector;

public class TextButton extends GameObject {
    private String textToDisplay;

    private PVector pos;

    private int textSize;

    public TextButton(Scene scene, String textToDisplay, PVector pos, int textSize) {
        super(scene);
        this.textToDisplay = textToDisplay;
        this.pos = pos;
        this.textSize = textSize;
    }


    public void render(){
        AsteroidsApplet.asteroidsApplet.fill(250);
        AsteroidsApplet.asteroidsApplet.textSize(textSize);
        AsteroidsApplet.asteroidsApplet.textAlign(PConstants.CENTER);
        AsteroidsApplet.asteroidsApplet.text(textToDisplay, this.pos.x ,this.pos.y);
    }
}
