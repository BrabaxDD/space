package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PApplet;
import processing.core.PVector;

public class AlienUFO  extends GameObject{
    //KOAM
    //Attribute
    private PVector pos;
    private PVector speed;
    private PVector endPos;
    private Boolean direction = true;

    public AlienUFO(PVector startPos, PVector enPos, Scene scene){
        super(scene);
        this.endPos = new PVector(-startPos.x+enPos.x,-startPos.y+enPos.y);
        this.speed = endPos.normalize();
        this.pos = startPos;
        this.speed.normalize();
    }

    @Override
    public void render(){
        AsteroidsApplet.asteroidsApplet.ellipse(pos.x,pos.y,20,20);
        if(direction){
            AsteroidsApplet.asteroidsApplet.line(this.pos.x, this.pos.y,this.pos.x+ this.speed.x*30,this.pos.y+ this.speed.y*30);
        }
    }

    @Override
    public void process(){
        pos.add(PVector.mult(speed,4));
    }

    public PVector getPos(){return pos;}
}
