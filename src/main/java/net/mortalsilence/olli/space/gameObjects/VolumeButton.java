package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.factorys.GameSceneFactory;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PConstants;
import processing.core.PVector;

public class VolumeButton extends Button {

    private Scene scene;

    private String textDisplayed;

    private boolean state;
    public VolumeButton(Scene scene, PVector pos, PVector diagonal, String textDisplayed) {
        super(scene, pos, diagonal);
        this.scene = scene;
        this.state = true;
        this.textDisplayed = textDisplayed;
    }

    @Override
    public void render(){
        super.render();
        AsteroidsApplet.asteroidsApplet.fill(0);
        AsteroidsApplet.asteroidsApplet.textSize(30);
        AsteroidsApplet.asteroidsApplet.textAlign(PConstants.CENTER);
        AsteroidsApplet.asteroidsApplet.text(textDisplayed, posLeftUp.x + this.diagonal.x/2 ,posLeftUp.y + this.diagonal.y/2 + 15);
    }

    @Override
    public void buttonPressed(){
        if(this.state){
        AsteroidsApplet.asteroidsApplet.setVolumeGlobal(0);
        this.textDisplayed = "Volume ON";
        this.state = false;
        }else{
            AsteroidsApplet.asteroidsApplet.setVolumeGlobal(1);
            this.textDisplayed = "Volume OFF";
            this.state = true;

        }
    }
}
