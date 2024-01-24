package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.AsteroidMovedListener;
import net.mortalsilence.olli.space.events.ButtonPressedListener;
import net.mortalsilence.olli.space.events.SpaceshipProjektileHitListener;
import net.mortalsilence.olli.space.events.SpaceshipProjektileMovedListener;
import net.mortalsilence.olli.space.scenes.Scene;
import net.mortalsilence.olli.space.utility.Keyboard;
import processing.core.PVector;

import java.awt.event.KeyEvent;

public class Spaceship extends GameObject implements ButtonPressedListener, AsteroidMovedListener, SpaceshipProjektileHitListener {

    private int level;
    private float exp;
    private PVector pos = new PVector(1000,500);
    private int size;
    private int cooldownTurret;
    private int cooldownTurretakt;

    public float turretTurnVelocity;
    private float velocity;
    private float bulletVelocity;
    PVector direction;
    PVector vel = new PVector(0,0);
    private boolean wPressed = false;

    public Spaceship(Scene scene) {
        super(scene);
        this.cooldownTurret = 30;
        this.cooldownTurretakt = 0;
        this.velocity = 15;
        this.turretTurnVelocity = (float) 1/45;
        this.direction = new PVector(1,0);
        this.vel = new PVector();
        this.bulletVelocity = 20.0F;
        this.size = 30;
        System.out.println("Debug: Initial turretTurningVelocity " + turretTurnVelocity);

    }

    @Override
    public void buttonPressed() {
        if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)){
            System.out.println("Debug: SpacePressed proccesed by Spaceship");
            wPressed = true;
            PVector traveldirection = new PVector(AsteroidsApplet.asteroidsApplet.mouseX - this.pos.x,AsteroidsApplet.asteroidsApplet.mouseY - this.pos.y);// schlecht programmiert posy   and posx direkt abgefragt
            traveldirection = traveldirection.normalize();
            PVector velmax = traveldirection.mult(velocity);// velmax ist die Geschwindigkeit die das Raumschiff anstrebt
            this.vel = this.vel.mult(0.95f).add(velmax.mult(0.05f));
        }
        if(Keyboard.isKeyPressed(KeyEvent.VK_A)){
            if(this.cooldownTurretakt <= 0) {
                this.cooldownTurretakt = this.cooldownTurret;
                this.direction = this.direction.normalize();
                PVector bulletdirection = new PVector(this.direction.x, this.direction.y);
                bulletdirection.mult(bulletVelocity);
                this.scene.addObject(new Projektile(this.scene, new PVector(this.pos.x, this.pos.y), new PVector(bulletdirection.x, bulletdirection.y)));
            }
        }
    }

    public void render(){
        AsteroidsApplet.asteroidsApplet.color(255,0,0);
        AsteroidsApplet.asteroidsApplet.stroke(255,0,0);
        AsteroidsApplet.asteroidsApplet.fill(255,0,0);
        AsteroidsApplet.asteroidsApplet.ellipse(pos.x,pos.y,30,30);
        AsteroidsApplet.asteroidsApplet.line(pos.x,pos.y,pos.x+direction.x*20,pos.y+direction.y*20);
        AsteroidsApplet.asteroidsApplet.color(0,0,0);
        AsteroidsApplet.asteroidsApplet.fill(0);
        AsteroidsApplet.asteroidsApplet.stroke(255);
        AsteroidsApplet.asteroidsApplet.rect(0,0,AsteroidsApplet.asteroidsApplet.width-1,30);
        AsteroidsApplet.asteroidsApplet.fill(0,255,0);
        AsteroidsApplet.asteroidsApplet.rect(1F, 1F, (float) (AsteroidsApplet.asteroidsApplet.width*exp/100/Math.pow(1.5,level)),28);
    }

    @Override
    public void process() {

        //facing Turret

        PVector v1 = new PVector(AsteroidsApplet.asteroidsApplet.mouseX - this.pos.x,AsteroidsApplet.asteroidsApplet.mouseY - this.pos.y);// schlecht programmiert posy   and posx direkt abgefragt
        v1 = v1.normalize(); // SOLL RICHTUNG
        //System.out.println("Debug: Heading of connection vector Mouse,Spaceship "+v1.heading());
        float theta = v1.heading()-direction.heading();
        //System.out.println("Debug: theta Spaceship " + theta);
        if(theta > Math.PI){
            theta = (float)(theta - 2*Math.PI);
        }
        if(theta < -Math.PI){
            theta = (float) (theta + 2*Math.PI);
        }
        //System.out.println("Debug: theta Spaceship " + theta);
        if(theta> turretTurnVelocity){
            theta = (float)Math.PI *turretTurnVelocity;
            //System.out.println("Debug: Spaceship turning right");
        }
        if(theta< -turretTurnVelocity){
            //System.out.println("Debug: Spaceship turning left");
            theta = -1*(float)Math.PI *turretTurnVelocity;
        }
        //System.out.println("Debug: Turret turning velocity Spaceship " + turretTurnVelocity);
        //System.out.println("Debug: theta Spaceship " + theta);
        direction = direction.rotate((float) ( theta));
        this.direction = this.direction.normalize();

        //Movement

        if(this.wPressed == false){
            this.vel = this.vel.mult(0.95f);
        }
        this.pos.add(vel);
        //System.out.println("Debug: Velx Spaceship: " + vel.x +" Vely Spaceship: " + vel.y);
        this.wPressed = false;

        //Turret Cooldown

        this.cooldownTurretakt = this.cooldownTurretakt -1;

    }

    @Override
    public void astroidMoved(Asteroid asteroid) {
        PVector astpos = new PVector(asteroid.getPos().x,asteroid.getPos().y);
        if(astpos.sub(this.pos).mag() < asteroid.getSize() + this.size){
            AsteroidsApplet.asteroidsApplet.background(255,0,0);
            System.out.println("Debug: Spaceship hit bei an Asteroid");
        }
    }

    @Override
    public void spaceshipProjektileHit(int exp) {
        this.exp = this.exp + exp;
        if(this.exp > 100* (Math.pow(1.5,level))){
            this.exp = 0;
            this.level++;
        }
    }
}
