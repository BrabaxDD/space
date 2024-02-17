package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.SpaceshipMovedListener;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PApplet;
import processing.core.PVector;

import static java.lang.Math.abs;

public class Item extends GameObject implements SpaceshipMovedListener, Runnable {
    private Scene scene;
    private int pickupRadius = 100;
    private PVector pos;
    private PVector speed = new PVector(0,0);
    private  PVector playerPos;
    private int lifespan; //in millisek
    private boolean toSee = true;
    public Item(Scene scene, PVector pos) {
        super(scene);
        this.scene = scene;
        this.pos = pos;
        this.scene.getEventbus().registerSpaceshipMovedListener(this);
        this.lifespan = 1000;
    }

    @Override
    public void render(){
        if(toSee) {
            if (AsteroidsApplet.asteroidsApplet.isDebugModeOn()) {
                AsteroidsApplet.asteroidsApplet.stroke(200);
                AsteroidsApplet.asteroidsApplet.circle(this.pos.x, this.pos.y, pickupRadius);
            }
            AsteroidsApplet.asteroidsApplet.stroke(100);
            AsteroidsApplet.asteroidsApplet.circle(this.pos.x, this.pos.y, 30);
        }
    }

    @Override
    public void spaceshipMoved(PVector pos) {
        this.playerPos = pos;
        if(abs(this.pos.dist(playerPos))<this.pickupRadius){
            speed = new PVector(playerPos.x-this.pos.x, playerPos.y-this.pos.y).normalize();
            this.pos.add(speed.mult(3));
        }
        if(abs(this.pos.dist(playerPos))<20){
           deleteItem();
        }
    }

    private void deleteItem(){
        this.scene.getEventbus().itemPickedUp(this);
        this.toSee = false;
        this.scene.getEventbus().deleteSpaceshipMovedListener(this);
        Thread timer = new Thread(this);
        timer.start();
    }

    @Override
    public void run() {
        int framesLeft = this.lifespan;
        try {
            Thread.sleep(lifespan);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.scene.getEventbus().itemTimeUp(this);

        this.scene.deleteObject(this);
    }
}