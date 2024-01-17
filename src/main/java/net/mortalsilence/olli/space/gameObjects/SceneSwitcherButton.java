package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.MouseLeftClickListener;
import net.mortalsilence.olli.space.factorys.GameSceneFactory;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PConstants;
import processing.core.PVector;

import java.util.ArrayList;

public class SceneSwitcherButton extends Button{
    public SceneSwitcherButton(Scene scene, PVector pos,PVector diagonal) {
        super(scene,pos,diagonal);
    }


    @Override
    public void render(){
        super.render();
        AsteroidsApplet.asteroidsApplet.fill(0);
        AsteroidsApplet.asteroidsApplet.textSize(30);
        AsteroidsApplet.asteroidsApplet.textAlign(PConstants.CENTER);
        AsteroidsApplet.asteroidsApplet.text("Start Game", posLeftUp.x + this.diagonal.x/2 ,posLeftUp.y + this.diagonal.y/2 + 15);
    }

    @Override
    public void buttonPressed(){
        AsteroidsApplet.asteroidsApplet.switchScene(new GameSceneFactory().buildGameScene());
    }

}
