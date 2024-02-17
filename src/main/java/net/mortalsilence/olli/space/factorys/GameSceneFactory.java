package net.mortalsilence.olli.space.factorys;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.gameObjects.*;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PApplet;
import processing.core.PVector;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

import static processing.core.PApplet.*;

public class GameSceneFactory {

    static public final int GAME = 0;
    static public final int HOME = 1;
    static public final int OVER = 2;
    static public final int HTP = 3;
    public static Scene buildGameScene(int wichSceneToRender){ //Das ist das Startfeld
        Scene s = new Scene(new ArrayList<>());
        switch (wichSceneToRender) {
            case 0:

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

                break;

            case 1:  //Das ist das Startmenü
                String[] options = PApplet.loadStrings(new File("src/main/java/net/mortalsilence/olli/space/options.txt"));
                float lo = Float.parseFloat(options[0]);
                float floa = Float.parseFloat(options[1]);
                println("floa: " + floa);

                Button b = new SceneSwitcherButton(AsteroidsApplet.asteroidsApplet.getActiveScene(), new PVector(((float) AsteroidsApplet.asteroidsApplet.displayWidth / 2) - 300, ((float) AsteroidsApplet.asteroidsApplet.displayHeight / 2) - 50), new PVector(600, 100), 0, "Start Game");
                s.getEventbus().registerEventMouseLeftClick(b);
                s.addObject(b);
                /*Button b1 = new VolumeButton(AsteroidsApplet.asteroidsApplet.getActiveScene(),new PVector(AsteroidsApplet.asteroidsApplet.displayWidth/10*7, AsteroidsApplet.asteroidsApplet.displayHeight/10*2),new PVector(200,100), "Volume On" );
                s.getEventbus().registerEventMouseLeftClick(b1);
                s.addObject(b1);*/

                VolumeSlider slider = new VolumeSlider(new PVector((float) AsteroidsApplet.asteroidsApplet.displayWidth / 10 * 7, (float) AsteroidsApplet.asteroidsApplet.displayHeight / 10), new PVector(400, 50), lo, 100, "Music Volume", s, false);
                s.getEventbus().registerEventMouseLeftClick(slider);
                s.addObject(slider);
                VolumeSlider fx = new VolumeSlider(new PVector((float) AsteroidsApplet.asteroidsApplet.displayWidth / 10 * 7, (float) AsteroidsApplet.asteroidsApplet.displayHeight / 10 * 3), new PVector(400, 50), floa, 100, "FX Volume", s, true);
                s.getEventbus().registerEventMouseLeftClick(fx);
                s.addObject(fx);
                b = new SceneSwitcherButton(AsteroidsApplet.asteroidsApplet.getActiveScene(), new PVector(((float) AsteroidsApplet.asteroidsApplet.displayWidth / 2) - 300, ((float) AsteroidsApplet.asteroidsApplet.displayHeight / 2) + 100), new PVector(600, 100), 3, "How To Play" );
                s.getEventbus().registerEventMouseLeftClick(b);
                s.addObject(b);

               /* b = new Button(AsteroidsApplet.asteroidsApplet.getActiveScene(), new PVector(((float) AsteroidsApplet.asteroidsApplet.displayWidth / 10*7) + 300, ((float) AsteroidsApplet.asteroidsApplet.displayHeight / 10 * 8) + 100), new PVector(120, 20) )
            {
                @Override
                public void buttonPressed() {
                    String eingabe = JOptionPane.showInputDialog("Ternimal: ");
                }
            };
                s.getEventbus().registerEventMouseLeftClick(b);
                s.addObject(b);*/

                b = new ConsoleButton(AsteroidsApplet.asteroidsApplet.getActiveScene(), new PVector(((float) AsteroidsApplet.asteroidsApplet.displayWidth / 10*7) + 300, ((float) AsteroidsApplet.asteroidsApplet.displayHeight / 10 * 8) + 100), new PVector(240, 40));
                s.getEventbus().registerEventMouseLeftClick(b);
                s.addObject(b);

                break;

            case 2: //Das ist das Game Over Menü
                String[] highString = AsteroidsApplet.asteroidsApplet.loadStrings("src/main/java/net/mortalsilence/olli/space/scores.txt");
                String[] cache = highString[0].replaceAll("\\s+", "").split(":");
                int highestLevel = Integer.parseInt(cache[0]);
                int highestExperience = Integer.parseInt(cache[1]);
                TextButton text3 = new TextButton(AsteroidsApplet.asteroidsApplet.getActiveScene(), "Highscore : " + highestLevel + " | " + highestExperience + "/" + (int) Math.pow(1.5, highestLevel) * 100, new PVector(((float) AsteroidsApplet.asteroidsApplet.displayWidth / 2), ((float) AsteroidsApplet.asteroidsApplet.displayHeight / 2) + 350), 50);
                s.addObject(text3);

                TextButton text1 = new TextButton(AsteroidsApplet.asteroidsApplet.getActiveScene(), "GAME OVER", new PVector(((float) AsteroidsApplet.asteroidsApplet.displayWidth / 2), ((float) AsteroidsApplet.asteroidsApplet.displayHeight / 2) - 250), 30);
                s.addObject(text1);
                AsteroidsApplet.asteroidsApplet.fill(0);

                GameObject g = AsteroidsApplet.asteroidsApplet.getActiveScene().getGameObjects().get(0);
                int l;
                int xp = 0;
                if (g.getClass() == Spaceship.class) {
                    l = ((Spaceship) g).getLevel();
                    xp = (int) ((Spaceship) g).getExp();
                } else l = 38803;


                TextButton text2 = new TextButton(AsteroidsApplet.asteroidsApplet.getActiveScene(), "Level: " + l + " | Xp: " + xp + "/" + (int) Math.pow(1.5, l) * 100, new PVector(((float) AsteroidsApplet.asteroidsApplet.displayWidth / 2), ((float) AsteroidsApplet.asteroidsApplet.displayHeight / 2) - 150), 30);
                s.addObject(text2);

                Button b1 = new SceneSwitcherButton(AsteroidsApplet.asteroidsApplet.getActiveScene(), new PVector(((float) AsteroidsApplet.asteroidsApplet.displayWidth / 2) - 300, ((float) AsteroidsApplet.asteroidsApplet.displayHeight / 2) - 50), new PVector(600, 100), 0, "Restart");
                s.getEventbus().registerEventMouseLeftClick(b1);
                s.addObject(b1);
                Button b2 = new SceneSwitcherButton(AsteroidsApplet.asteroidsApplet.getActiveScene(), new PVector(((float) AsteroidsApplet.asteroidsApplet.displayWidth / 2) - 300, ((float) AsteroidsApplet.asteroidsApplet.displayHeight / 2) + 150), new PVector(600, 100), 1, "Home");
                s.getEventbus().registerEventMouseLeftClick(b2);
                s.addObject(b2);
                break;

            case 3: //How To Play

                ScrollText sc1 = new ScrollText(s, AsteroidsApplet.asteroidsApplet.loadStrings("src/main/java/net/mortalsilence/olli/space/HowToPlay.txt"), 20 );
                s.addObject(sc1);
                b = new SceneSwitcherButton(AsteroidsApplet.asteroidsApplet.getActiveScene(), new PVector(((float) AsteroidsApplet.asteroidsApplet.displayWidth / 10) *7, ((float) AsteroidsApplet.asteroidsApplet.displayHeight / 10)), new PVector(300, 50), 1, "Home" );
                s.getEventbus().registerEventMouseLeftClick(b);
                s.addObject(b);
                break;
        }
        return s;
    }

}
