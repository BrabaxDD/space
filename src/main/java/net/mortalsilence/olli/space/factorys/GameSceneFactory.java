package net.mortalsilence.olli.space.factorys;

import net.mortalsilence.olli.space.gameObjects.Asteroid;
import net.mortalsilence.olli.space.gameObjects.Spaceship;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PVector;

import java.util.ArrayList;

public class GameSceneFactory {
    public Scene buildGameScene(){
        Scene s = new Scene(new ArrayList<>());
        Spaceship ship = new Spaceship(s);
        s.getEventbus().registerEventButtonPressed(ship);
        s.getEventbus().registerAsteroidMovedListener(ship);
        s.addObject(ship);
        Asteroid a1 = new Asteroid(3,50,200,200,new PVector(0,1),s);
        a1.setPos(new PVector(200,200));
        a1.setSpeed(new PVector(0,0));
        s.addObject(a1);
        a1.asteroidHit();
        return s;
    }
}
