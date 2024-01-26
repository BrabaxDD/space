package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.SpaceshipProjektileHitListener;
import net.mortalsilence.olli.space.events.SpaceshipProjektileMovedListener;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PVector;

public class Projektile extends GameObject implements SpaceshipProjektileMovedListener {
    PVector pos;
    PVector vel;


    public Projektile(Scene scene,PVector pos,PVector vel) {
        super(scene);
        this.pos = pos;
        this.vel = vel;
        this.scene.getEventbus().registerSpaceshipProjektileMovedListener(this);
    }

    @Override
    public void process(){
        this.pos =this.pos.add(this.vel);
        this.scene.getEventbus().ProjektileMoved((int)pos.x,(int)pos.y,this);
        this.scene.getEventbus().spaceshipProjektileMoved(pos,this);
        if(this.pos.x > AsteroidsApplet.asteroidsApplet.width+ 60 || this.pos.x < - 60 || this.pos.y > AsteroidsApplet.asteroidsApplet.height +60 || this.pos.y < -60){
            this.deleteProjectile();
        }

    }
    @Override
    public void render(){
        AsteroidsApplet.asteroidsApplet.color(0,255,0);
        AsteroidsApplet.asteroidsApplet.stroke(0,255,0);
        AsteroidsApplet.asteroidsApplet.fill(0,255,0);
        AsteroidsApplet.asteroidsApplet.square(this.pos.x-5,this.pos.y - 5,10);

    }

    private void deleteProjectile() {
        this.scene.getEventbus().deleteSpaceshipProjektileMovedListener(this);
        this.scene.deleteObject(this);
    }

    @Override
    public void spaceshipProjektileMoved(PVector pos, Projektile projektile) {

    }
}
