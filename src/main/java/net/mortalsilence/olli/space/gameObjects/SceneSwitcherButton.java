package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.MouseLeftClickListener;
import net.mortalsilence.olli.space.factorys.GameSceneFactory;
import net.mortalsilence.olli.space.scenes.Scene;

import java.util.ArrayList;

public class SceneSwitcherButton extends Button{
    public SceneSwitcherButton(Scene scene) {
        super(scene);
    }

    @Override
    public void mouseLeftClick (int x,int y){
        AsteroidsApplet.asteroidsApplet.switchScene(new GameSceneFactory().buildGameScene());
    }

}
