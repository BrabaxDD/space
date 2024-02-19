package net.mortalsilence.olli.space.gameObjects.GUI;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.factorys.GameSceneFactory;
import net.mortalsilence.olli.space.gameObjects.GUI.Button;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PConstants;
import processing.core.PVector;

public class SceneSwitcherButton extends Button {

     private int toWichScene;

    private String textDisplayed;
    public SceneSwitcherButton(Scene scene, PVector pos,PVector diagonal, int toWichScene, String textToRender) {
        super(scene,pos,diagonal);
        this.toWichScene = toWichScene;
        textDisplayed = textToRender;
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
        AsteroidsApplet.asteroidsApplet.switchScene(GameSceneFactory.buildGameScene(this.toWichScene));
    }

}
