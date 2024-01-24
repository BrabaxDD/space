package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.SpaceshipMovedListener;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PApplet;
import processing.core.PVector;

import static java.lang.Math.abs;
import static processing.core.PApplet.println;

public class AlienUFO  extends GameObject implements SpaceshipMovedListener {
    //KOAM
    //Attribute
    private final PVector pos;
    private PVector speed;


    private  PVector targetPoint= new PVector(0,0);
    private final Boolean direction = true;

    boolean start = false;

    public AlienUFO(PVector startPos, Scene scene){
        super(scene);
        this.pos = startPos;
        this.scene.getEventbus().registerSpaceshipMovedListener(this);


        this.speed = new PVector(0,0);
    }

    @Override
    public void render(){
        AsteroidsApplet.asteroidsApplet.ellipse(pos.x,pos.y,20,20);
        AsteroidsApplet.asteroidsApplet.ellipse(targetPoint.x,targetPoint.y,20,20);
        if(direction){
            AsteroidsApplet.asteroidsApplet.line(this.pos.x, this.pos.y,this.pos.x+ this.speed.x*30,this.pos.y+ this.speed.y*30);
        }
    }

    @Override
    public void process(){
        this.pos.add(PVector.mult(speed,4));
    }

    public PVector getPos(){return pos;}

    //private void setTarget(PVector newTarget) { this.targetPoint= newTarget;this.targetDirection = new PVector(-this.pos.x+targetPoint.x,-this.pos.y+targetPoint.y);}

    @Override
    public void spaceshipMoved( PVector pos) {
        if((abs(this.pos.dist(this.targetPoint)) <=  30) || (this.pos.x == 0 && this.targetPoint.y == 0)){
            println("new Target");
            this.targetPoint = pos;
            this.speed  = new PVector((targetPoint.x-this.pos.x),(targetPoint.y-this.pos.y)).normalize();
        }
    }
}
