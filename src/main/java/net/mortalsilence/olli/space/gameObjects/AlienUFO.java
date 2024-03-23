package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.AlienUFOMovedListener;
import net.mortalsilence.olli.space.events.SpaceshipMovedListener;
import net.mortalsilence.olli.space.events.SpaceshipProjektileMovedListener;
import net.mortalsilence.olli.space.scenes.Scene;
import net.mortalsilence.olli.space.utility.Particle_effect;
import org.jetbrains.annotations.NotNull;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;

import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static processing.core.PApplet.ARGS_DISPLAY;
import static processing.core.PApplet.println;
import static processing.core.PConstants.CENTER;

public class AlienUFO  extends GameObject implements SpaceshipMovedListener, SpaceshipProjektileMovedListener, AlienUFOMovedListener {
    //KOAM
    //Attribute
    private final PVector pos;
    private PVector speed;

    private final int size = 30;

    private  PVector targetPoint= new PVector(0,0);
    private final Boolean direction;
    private int cooldownTurretakt;
    private int cooldownTurret;

    private PVector shootingTarget;

    private float bulletVelocity;

    private PImage textureBase;

    private PImage textureTurrent;

    public AlienUFO(PVector startPos, Scene scene){
        super(scene);
        this.pos = startPos;
        this.scene.getEventbus().registerSpaceshipMovedListener(this);
        this.scene.getEventbus().registerSpaceshipProjektileMovedListener(this);
        this.scene.getEventbus().registerAlienUFOMovedListeners(this);
        this.shootingTarget = new PVector(0,0);

        this.speed = new PVector(0,0);
        direction = AsteroidsApplet.asteroidsApplet.isDebugModeOn();
        this.cooldownTurretakt = (int) AsteroidsApplet.asteroidsApplet.random(AsteroidsApplet.asteroidsApplet.getGameRule(8),AsteroidsApplet.asteroidsApplet.getGameRule(9));
        this.cooldownTurret = (int) AsteroidsApplet.asteroidsApplet.random(AsteroidsApplet.asteroidsApplet.getGameRule(8),AsteroidsApplet.asteroidsApplet.getGameRule(9));
        this.bulletVelocity = AsteroidsApplet.asteroidsApplet.getGameRule(7);
        textureBase = AsteroidsApplet.asteroidsApplet.loadImage(AsteroidsApplet.ADRESS_TO_SPACE+"textures"+ File.separator+"AlienUFO_base.png");
        textureTurrent = AsteroidsApplet.asteroidsApplet.loadImage(AsteroidsApplet.ADRESS_TO_SPACE+"textures"+File.separator+"AlienUFO_turrent.png");
    }

    @Override
    public void render(){
        //AsteroidsApplet.asteroidsApplet.ellipse(pos.x,pos.y,this.size,this.size);

        if(direction){
            AsteroidsApplet.asteroidsApplet.line(this.pos.x, this.pos.y,this.targetPoint.x, this.targetPoint.y);
            AsteroidsApplet.asteroidsApplet.line(this.pos.x, this.pos.y,this.pos.x+ this.speed.x*30,this.pos.y+ this.speed.y*30);
        }

        float alpha = (float) (AsteroidsApplet.atan2(this.targetPoint.y - this.pos.y, this.targetPoint.x- this.pos.x) +89.55);
        AsteroidsApplet.asteroidsApplet.imageMode(CENTER);
        AsteroidsApplet.asteroidsApplet.pushMatrix();
        AsteroidsApplet.asteroidsApplet.translate(this.pos.x, this.pos.y);
        AsteroidsApplet.asteroidsApplet.rotate((alpha));
        AsteroidsApplet.asteroidsApplet.image(textureBase, 0,0);
        AsteroidsApplet.asteroidsApplet.translate(0,0);
        AsteroidsApplet.asteroidsApplet.popMatrix();
        alpha = (float) (AsteroidsApplet.atan2(this.shootingTarget.y - this.pos.y, this.shootingTarget.x- this.pos.x) +89.55);
        AsteroidsApplet.asteroidsApplet.imageMode(CENTER);
        AsteroidsApplet.asteroidsApplet.pushMatrix();
        AsteroidsApplet.asteroidsApplet.translate(this.pos.x, this.pos.y);
        AsteroidsApplet.asteroidsApplet.rotate((alpha));
        AsteroidsApplet.asteroidsApplet.image(textureTurrent, 0,0);
        AsteroidsApplet.asteroidsApplet.translate(0,0);
        AsteroidsApplet.asteroidsApplet.popMatrix();
    }

    @Override
    public void process(){
        this.pos.add(PVector.mult(speed,4));
        //this.scene.getEventbus().alienUFOMoved(this);


        if(this.cooldownTurretakt <= 0) {
            this.cooldownTurretakt = this.cooldownTurret;
            PVector bulletdirection = new PVector(this.shootingTarget.x-this.pos.x, this.shootingTarget.y-this.pos.y).normalize();
            bulletdirection.mult(bulletVelocity);
            PVector bullet =  new PVector(bulletdirection.x, bulletdirection.y);
            this.scene.addObject(new Projektile(this.scene, new PVector((float) (this.pos.x+bullet.x*3), (float) (this.pos.y+bullet.y*3)), bullet, this));

            AsteroidsApplet.asteroidsApplet.getFxPlayer().playSound(0);
        }
        this.cooldownTurretakt --;

    }

    public PVector getPos(){return pos;}

    //private void setTarget(PVector newTarget) { this.targetPoint= newTarget;this.targetDirection = new PVector(-this.pos.x+targetPoint.x,-this.pos.y+targetPoint.y);}

    @Override
    public void spaceshipMoved( PVector pos) {
        this.shootingTarget = pos;
        if((abs(this.pos.dist(this.targetPoint)) <=  this.size) || (this.targetPoint.x == 0 && this.targetPoint.y == 0)){
            this.targetPoint = pos;
            this.speed  = new PVector((targetPoint.x-this.pos.x),(targetPoint.y-this.pos.y)).normalize();
        }
    }

    public void ufoHit(){
        this.scene.getEventbus().spaceshipProjektileHit(20);
        this.scene.getEventbus().deleteSpaceshipMovedListener(this);
        this.scene.getEventbus().deleteSpaceshipProjektileMovedListener(this);
        AsteroidsApplet.asteroidsApplet.getFxPlayer().playSound(2);

        int item = (int) AsteroidsApplet.asteroidsApplet.random(0, 3);
        System.out.println(item);
        if (item == 1) {
            this.scene.addObject(new BombItem(this.scene, this.pos,(int) AsteroidsApplet.asteroidsApplet.random(2)));
        }
        AsteroidsApplet.asteroidsApplet.newEffect(this.pos);
        this.scene.deleteObject(this);

    }

    public int getSize(){
        return this.size;
    }

    @Override
    public  void spaceshipProjektileMoved ( Projektile projektile,GameObject shooter, GameObject target){
        if( target == this ){
            System.out.println("\\u001B[31mDebug: Projectile hit an AlienUFO");
            this.scene.deleteObject(projektile);
            this.scene.getEventbus().deleteSpaceshipProjektileMovedListener(projektile);
            this.ufoHit();

        }
    }


    @Override
    public void alienUFOMoved(AlienUFO ufo) {
        if(ufo == this){

            this.ufoHit();
        }
    }
}
