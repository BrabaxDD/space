package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.AlienUFOMovedListener;
import net.mortalsilence.olli.space.events.SpaceshipMovedListener;
import net.mortalsilence.olli.space.events.SpaceshipProjektileMovedListener;
import net.mortalsilence.olli.space.scenes.Scene;
import org.jetbrains.annotations.NotNull;
import processing.core.PApplet;
import processing.core.PVector;

import static java.lang.Math.abs;
import static processing.core.PApplet.println;

public class AlienUFO  extends GameObject implements SpaceshipMovedListener, SpaceshipProjektileMovedListener {
    //KOAM
    //Attribute
    private final PVector pos;
    private PVector speed;

    private final int size = 30;

    private  PVector targetPoint= new PVector(0,0);
    private final Boolean direction = true;

    boolean start = false;

    public AlienUFO(PVector startPos, Scene scene){
        super(scene);
        this.pos = startPos;
        this.scene.getEventbus().registerSpaceshipMovedListener(this);
        this.scene.getEventbus().registerSpaceshipProjektileMovedListener(this);

        this.speed = new PVector(0,0);
    }

    @Override
    public void render(){
        AsteroidsApplet.asteroidsApplet.ellipse(pos.x,pos.y,this.size,this.size);
        if(direction){
            AsteroidsApplet.asteroidsApplet.line(this.pos.x, this.pos.y,this.pos.x+ this.speed.x*30,this.pos.y+ this.speed.y*30);
        }
    }

    @Override
    public void process(){
        this.pos.add(PVector.mult(speed,4));
        this.scene.getEventbus().alienUFOMoved(this);
    }

    public PVector getPos(){return pos;}

    //private void setTarget(PVector newTarget) { this.targetPoint= newTarget;this.targetDirection = new PVector(-this.pos.x+targetPoint.x,-this.pos.y+targetPoint.y);}

    @Override
    public void spaceshipMoved( PVector pos) {
        if((abs(this.pos.dist(this.targetPoint)) <=  this.size) || (this.pos.x == 0 && this.targetPoint.y == 0)){
            this.targetPoint = pos;
            this.speed  = new PVector((targetPoint.x-this.pos.x),(targetPoint.y-this.pos.y)).normalize();
        }
    }

    void ufoHit(){
        this.scene.getEventbus().spaceshipProjektileHit(20);
        this.scene.getEventbus().deleteSpaceshipMovedListener(this);
        this.scene.getEventbus().deleteSpaceshipProjektileMovedListener(this);
        this.scene.deleteObject(this);
    }

    public int getSize(){
        return this.size;
    }

    @Override
    public  void spaceshipProjektileMoved (@NotNull PVector pos, Projektile projektile){
        if(pos.dist(this.pos) < this.size){
            System.out.println("\\u001B[31mDebug: Projectile hit an AlienUFO");
            this.scene.deleteObject(projektile);
            this.scene.getEventbus().deleteSpaceshipProjektileMovedListener(projektile);
            this.ufoHit();

        }
    }


}
