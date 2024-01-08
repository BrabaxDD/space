package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PVector;

public class Projektile extends GameObject{
    PVector pos;
    PVector vel;


    public Projektile(Scene scene,PVector pos,PVector vel) {
        super(scene);
        this.pos = pos;
        this.vel = vel;
    }

    @Override
    public void process(){
        this.pos =this.pos.add(this.vel);
        this.scene.getEventbus().ProjektileMoved((int)pos.x,(int)pos.y);
    }
    @Override
    public void render(){
        AsteroidsApplet.asteroidsApplet.square(this.pos.x-5,this.pos.y - 5,10);

    }

}
