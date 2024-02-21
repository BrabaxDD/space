package net.mortalsilence.olli.space.gameObjects;

import com.sun.javafx.application.ParametersImpl;
import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.SpaceshipMovedListener;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;

import static java.lang.Math.abs;

public class Item extends GameObject implements SpaceshipMovedListener, Runnable {
    private final Scene scene;
    private final int pickupRadius = 100;
    private final PVector pos;
    private int lifespan; //in millisek
    private boolean toSee = true;
    int framesLeft = this.lifespan;

    private PImage texture;

    private int type;   //type = 1 --> Shield  type = 2 --> EMPblast

    public Item(Scene scene,int type, PVector pos, int lifespan) {
        super(scene);
        this.scene = scene;
        this.pos = pos;
        this.scene.getEventbus().registerSpaceshipMovedListener(this);
        this.lifespan = lifespan;
        switch (type){
            case 1:
                texture = AsteroidsApplet.asteroidsApplet.loadImage(AsteroidsApplet.ADRESS_TO_SPACE+"textures"+ File.separator+"ShieldItem.png");
                break;
            case 2:
                texture = AsteroidsApplet.asteroidsApplet.loadImage(AsteroidsApplet.ADRESS_TO_SPACE+"textures"+ File.separator+"EMP.png");
                break;
        }
        this.type = type;
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
            AsteroidsApplet.asteroidsApplet.image(texture, this.pos.x, this.pos.y);

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
        AsteroidsApplet.asteroidsApplet.getFxPlayer().playSound(5);
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

    public int getType(){
        return this.type;
    }

    @Override
    public PVector getPos(){
        return this.pos;
    }

}