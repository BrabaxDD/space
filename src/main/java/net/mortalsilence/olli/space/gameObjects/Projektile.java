package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.SpaceshipProjektileHitListener;
import net.mortalsilence.olli.space.events.SpaceshipProjektileMovedListener;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;

import static processing.core.PConstants.CENTER;

public class Projektile extends GameObject implements SpaceshipProjektileMovedListener {
    PVector pos;
    PVector vel;

    private GameObject shooter;

    private PImage texture;

    public Projektile(Scene scene,PVector pos,PVector vel, GameObject shoot) {
        super(scene);
        this.pos = pos;
        this.vel = vel;
        this.scene.getEventbus().registerSpaceshipProjektileMovedListener(this);
        this.shooter = shoot;
        if(this.shooter.getClass() == Spaceship.class) {
            this.texture = AsteroidsApplet.asteroidsApplet.loadImage(AsteroidsApplet.ADRESS_TO_SPACE+"textures"+ File.separator+"Projektile.png");
        }else{
            this.texture = AsteroidsApplet.asteroidsApplet.loadImage(AsteroidsApplet.ADRESS_TO_SPACE+"textures"+File.separator+"Projektile_AlienUFO.png");
        }
    }

    @Override
    public void process(){
        this.pos =this.pos.add(this.vel);
        this.scene.getEventbus().ProjektileMoved((int)pos.x,(int)pos.y,this);
        //this.scene.getEventbus().spaceshipProjektileMoved(this, shooter);
        if(this.pos.x > AsteroidsApplet.asteroidsApplet.width+ 60 || this.pos.x < - 60 || this.pos.y > AsteroidsApplet.asteroidsApplet.height +60 || this.pos.y < -60){
            this.deleteProjectile();
        }

    }
    @Override
    public void render(){
        //PVector cache = this.vel.normalize();
        float alpha = (float) (AsteroidsApplet.atan2(vel.y ,  vel.x) +89.55);
        AsteroidsApplet.asteroidsApplet.imageMode(CENTER);
        AsteroidsApplet.asteroidsApplet.pushMatrix();
        AsteroidsApplet.asteroidsApplet.translate(this.pos.x, this.pos.y);
        AsteroidsApplet.asteroidsApplet.rotate((alpha));
        AsteroidsApplet.asteroidsApplet.image(texture, 0,0);
        AsteroidsApplet.asteroidsApplet.translate(0,0);
        AsteroidsApplet.asteroidsApplet.popMatrix();
        /*AsteroidsApplet.asteroidsApplet.color(0,255,0);
        AsteroidsApplet.asteroidsApplet.stroke(0,255,0);
        AsteroidsApplet.asteroidsApplet.fill(0,255,0);
        AsteroidsApplet.asteroidsApplet.square(this.pos.x-5,this.pos.y - 5,10);*/

    }

    private void deleteProjectile() {
        this.scene.getEventbus().deleteSpaceshipProjektileMovedListener(this);
        this.scene.deleteObject(this);
    }

    @Override
    public PVector getPos(){return this.pos;}

    @Override
    public void spaceshipProjektileMoved(Projektile projektile,GameObject shooter, GameObject target) {
        if(projektile == this){
            deleteProjectile();
        }
    }

    public GameObject getShooter(){return shooter;}
}
