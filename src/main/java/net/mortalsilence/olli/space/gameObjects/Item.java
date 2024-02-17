package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.SpaceshipMovedListener;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PVector;

import static java.lang.Math.abs;

public class Item extends GameObject implements SpaceshipMovedListener, Runnable {
    private final Scene scene;
    private final int pickupRadius = 100;
    private final PVector pos;
    private int lifespan; //in millisek
    private boolean toSee = true;
    int framesLeft = this.lifespan;

    public Item(Scene scene, PVector pos) {
        super(scene);
        this.scene = scene;
        this.pos = pos;
        this.scene.getEventbus().registerSpaceshipMovedListener(this);
        this.lifespan = 1000;
    }

    @Override
    public void render() {
        if (toSee) {
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
        if (abs(this.pos.dist(pos)) < this.pickupRadius) {
            PVector speed = new PVector(pos.x - this.pos.x, pos.y - this.pos.y).normalize();
            this.pos.add(speed.mult(3));
        }
        if (abs(this.pos.dist(pos)) < 20) {
            deleteItem();
        }
    }

    private void deleteItem() {
        this.scene.getEventbus().itemPickedUp(this);
        this.toSee = false;
        this.scene.getEventbus().deleteSpaceshipMovedListener(this);
        Thread timer = new Thread(this);
        timer.start();
    }

    @Override
    public void run() {
        framesLeft = this.lifespan;

        for (int i = 0; i < lifespan; i++) {
            try {
                Thread.sleep(1);
                framesLeft--;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        this.scene.getEventbus().itemTimeUp(this);

        this.scene.deleteObject(this);
    }

    public int getTimeLeft() {
        return this.framesLeft;
    }

}