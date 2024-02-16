package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.PlayerLevelListener;
import net.mortalsilence.olli.space.events.SpaceshipMovedListener;
import net.mortalsilence.olli.space.gameObjects.Asteroid;
import net.mortalsilence.olli.space.gameObjects.GameObject;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PApplet;
import processing.core.PVector;

public class Spawner extends GameObject implements SpaceshipMovedListener, PlayerLevelListener {
    private float spawnrateAsteroid; //in Asteroiden * s^-1 ungefähr

    private float spawnrateAlienUFO;

    private Scene scene;

    private PVector playerPos;

    private int[] timer = new int[2];

    private int playerLevel;

    private int playerXP;

    private int playerLevelPrevious;

    public Spawner(Scene s){
        super(s);
        this.spawnrateAlienUFO = (float) 0.25;
        this.spawnrateAsteroid = (float) 0.5;
        this.scene = s;
        this.scene.getEventbus().registerSpaceshipMovedListener(this);
        this.scene.getEventbus().registerPlayerLevelListener(this);

    }
    @Override
    public void process(){
        if(timer[0] >= AsteroidsApplet.asteroidsApplet.frameRate* 1/spawnrateAsteroid ){
            PVector test = new PVector((int)AsteroidsApplet.asteroidsApplet.random(0, AsteroidsApplet.asteroidsApplet.width),(int)AsteroidsApplet.asteroidsApplet.random(0 , AsteroidsApplet.asteroidsApplet.height));
            int sizeTest = (int)AsteroidsApplet.asteroidsApplet.random(100,150);
            while(playerPos.dist(test) < (float) sizeTest /2 + 140){
                test = new PVector((int)AsteroidsApplet.asteroidsApplet.random(0, AsteroidsApplet.asteroidsApplet.width),(int)AsteroidsApplet.asteroidsApplet.random(0 , AsteroidsApplet.asteroidsApplet.height));
            }
            this.scene.addObject((new Asteroid(3,120,(int)test.x,(int)test.y,new PVector(AsteroidsApplet.asteroidsApplet.random(-1,1),AsteroidsApplet.asteroidsApplet.random(-1,1)),scene)));
            timer[0] = 0;
            AsteroidsApplet.asteroidsApplet.println("new Asteroid spawned!");
        }
        if(timer[1] >= AsteroidsApplet.asteroidsApplet.frameRate* 1/spawnrateAlienUFO){
            int type = (int)AsteroidsApplet.asteroidsApplet.random(0,2);
            if(type == 0) {
                this.scene.addObject((new AlienUFO(new PVector(0, (int) AsteroidsApplet.asteroidsApplet.random(0, AsteroidsApplet.asteroidsApplet.height)), this.scene)));
            }else
                this.scene.addObject((new AlienUFO(new PVector(AsteroidsApplet.asteroidsApplet.width, (int) AsteroidsApplet.asteroidsApplet.random(0, AsteroidsApplet.asteroidsApplet.height)), this.scene)));
            timer[1] = 0;
            AsteroidsApplet.asteroidsApplet.println("new Asteroid spawned!");
        }
        for(int i=0; i< timer.length; i++){
            timer[i]++;
        }
        if(playerLevel != playerLevelPrevious){
            this.spawnrateAlienUFO += (float) playerLevel /20 ;
            this.spawnrateAsteroid += (float) playerLevel /10;

            playerLevelPrevious = playerLevel;
        }


        if(AsteroidsApplet.asteroidsApplet.isDebugModeOn()){
            AsteroidsApplet.asteroidsApplet.fill(250,130,145);
            AsteroidsApplet.asteroidsApplet.text("Spawnrate Asteroids: "+this.spawnrateAsteroid+"/s" , AsteroidsApplet.asteroidsApplet.width-250,250);
            AsteroidsApplet.asteroidsApplet.text("Spawnrate AlienUFO: "+ this.spawnrateAlienUFO+"/s" ,AsteroidsApplet.asteroidsApplet.width-250,200);
        }
    }

    @Override
    public void spaceshipMoved(PVector pos) {
        this.playerPos = pos;
    }

    @Override
    public void playerLevelListen(int level, int xp) {
        this.playerLevel = level;
        this.playerXP = level;
    }
}
