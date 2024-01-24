package net.mortalsilence.olli.space.factorys;

import javafx.scene.control.Alert;
import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.gameObjects.AlienUFO;
import net.mortalsilence.olli.space.gameObjects.Asteroid;
import net.mortalsilence.olli.space.gameObjects.Spaceship;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Collections;

public class GameSceneFactory {
    public Scene buildGameScene(){
        Scene s = new Scene(new ArrayList<>());
        Spaceship ship = new Spaceship(s);
        s.getEventbus().registerEventButtonPressed(ship);
        s.getEventbus().registerAsteroidMovedListener(ship);
        s.addObject(ship);
        Asteroid a1 = new Asteroid(3,120,(int)AsteroidsApplet.asteroidsApplet.random(0, AsteroidsApplet.asteroidsApplet.width),(int)AsteroidsApplet.asteroidsApplet.random(0 , AsteroidsApplet.asteroidsApplet.height),new PVector(0,1),s);
        a1.setSpeed(new PVector(1,0));
        s.addObject(a1);
        AlienUFO Alu1 = new AlienUFO(new PVector(0,(int)AsteroidsApplet.asteroidsApplet.random(0, PApplet.DEFAULT_HEIGHT)) ,s);
        s.addObject(Alu1);
        return s;
    }
}
