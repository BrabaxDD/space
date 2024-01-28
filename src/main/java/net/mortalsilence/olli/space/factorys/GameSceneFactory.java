package net.mortalsilence.olli.space.factorys;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.gameObjects.*;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

import static processing.core.PApplet.println;

public class GameSceneFactory {
    public static Scene buildGameScene(int wichSceneToRender){ //Das ist das Startfeld
        Scene s = new Scene(new ArrayList<>());
        if (wichSceneToRender == 0) {


            //WICHTIG: DAS SPACESHIP ZUERST DER SZENE HINZUFÜGEN!!!! (für game over screen)

        Spaceship ship = new Spaceship(s);
        s.getEventbus().registerEventButtonPressed(ship);
        s.getEventbus().registerAsteroidMovedListener(ship);
        s.getEventbus().registerSpaceshipProjektileHitListener(ship);
        s.addObject(ship);
        /*Asteroid a1 = new Asteroid(3,120,(int)AsteroidsApplet.asteroidsApplet.random(0, AsteroidsApplet.asteroidsApplet.width),(int)AsteroidsApplet.asteroidsApplet.random(0 , AsteroidsApplet.asteroidsApplet.height),new PVector(0,1),s);
        a1.setSpeed(new PVector(1,0));
        s.addObject(a1);
        Asteroid a2 = new Asteroid(3,150,200,200,new PVector(0,1),s);
        a2.setPos(new PVector(200,200));
        a2.setSpeed(new PVector(1,0));
        s.addObject(a2);
        Asteroid a3 = new Asteroid(3,150,200,200,new PVector(0,1),s);
        a3.setPos(new PVector(200,200));
        a3.setSpeed(new PVector(1,0));
        s.addObject(a3);
        AlienUFO Alu1 = new AlienUFO(new PVector(0,(int)AsteroidsApplet.asteroidsApplet.random(0, PApplet.DEFAULT_HEIGHT)) ,s);
        s.addObject(Alu1);*/
        Spawner spawner = new Spawner(s);
        s.addObject(spawner);

        }

        if(wichSceneToRender == 1 ){  //Das ist das Startmenü

            Button b = new SceneSwitcherButton(AsteroidsApplet.asteroidsApplet.getActiveScene(),new PVector((AsteroidsApplet.asteroidsApplet.displayWidth/2)-300,(AsteroidsApplet.asteroidsApplet.displayHeight/2) -50),new PVector(600,100), 0, "Start Game");
            s.getEventbus().registerEventMouseLeftClick(b);
            s.addObject(b);
            println("Button activated");
        }

        if(wichSceneToRender == 2){ //Das ist das Game Over Menü

            TextButton text1 = new TextButton(AsteroidsApplet.asteroidsApplet.getActiveScene(),"GAME OVER", new PVector(((float) AsteroidsApplet.asteroidsApplet.displayWidth /2),((float) AsteroidsApplet.asteroidsApplet.displayHeight /2) -250) ,30);
            s.addObject(text1);
            AsteroidsApplet.asteroidsApplet.fill(0);

            GameObject g = AsteroidsApplet.asteroidsApplet.getActiveScene().getGameObjects().get(0);
            int l = 0;
            if(g.getClass() == Spaceship.class){
                l = ((Spaceship) g).getLevel();
            }

            TextButton text2 = new TextButton(AsteroidsApplet.asteroidsApplet.getActiveScene(),"Level: "+l, new PVector(((float) AsteroidsApplet.asteroidsApplet.displayWidth /2),((float) AsteroidsApplet.asteroidsApplet.displayHeight /2) -150) ,30);
            s.addObject(text2);

            Button b1 = new SceneSwitcherButton(AsteroidsApplet.asteroidsApplet.getActiveScene(),new PVector(((float) AsteroidsApplet.asteroidsApplet.displayWidth /2)-300,((float) AsteroidsApplet.asteroidsApplet.displayHeight /2) -50),new PVector(600,100), 0, "Restart");
            s.getEventbus().registerEventMouseLeftClick(b1);
            s.addObject(b1);
            Button b2 = new SceneSwitcherButton(AsteroidsApplet.asteroidsApplet.getActiveScene(),new PVector(((float) AsteroidsApplet.asteroidsApplet.displayWidth /2)-300,((float) AsteroidsApplet.asteroidsApplet.displayHeight /2) +150),new PVector(600,100), 1, "Home");
            s.getEventbus().registerEventMouseLeftClick(b2);
            s.addObject(b2);
        }

        return s;
    }
}
