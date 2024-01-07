package net.mortalsilence.olli.space.factorys;

import net.mortalsilence.olli.space.gameObjects.Spaceship;
import net.mortalsilence.olli.space.scenes.Scene;

import java.util.ArrayList;

public class GameSceneFactory {
    public Scene buildGameScene(){
        Scene s = new Scene(new ArrayList<>());
        Spaceship ship = new Spaceship();
        s.getEventbus().registerEventButtonPressed(ship);
        s.addObject(ship);
        return s;
    }
}
