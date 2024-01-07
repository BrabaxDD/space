package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.ButtonPressedListener;
import processing.core.PVector;

public class Spaceship extends GameObject implements ButtonPressedListener{
    PVector pos = new PVector(0,0);
    PVector vel = new PVector(0,0);
    private boolean wPressed = false;
    @Override
    public void buttonPressed() {
        if(AsteroidsApplet.asteroidsApplet.key == ' '){
            System.out.println("Debug: SpacePressed proccesed by Spaceship");
            wPressed = true;
            PVector v1 = new PVector(AsteroidsApplet.asteroidsApplet.mouseX - this.pos.x,AsteroidsApplet.asteroidsApplet.mouseY - this.pos.y);
            PVector velmax = v1.mult(1/((float) Math.sqrt(v1.x*v1.x+v1.y*v1.y))).mult(20);// velmax ist die Geschwindigkeit die das Raumschiff anstrebt
            this.vel = this.vel.mult(0.95f).add(velmax.mult(0.05f));
        }
    }

    public void render(){
        AsteroidsApplet.asteroidsApplet.ellipse(pos.x,pos.y,30,30);
    }

    @Override
    public void process() {
        if(this.wPressed == false){
            this.vel = this.vel.mult(0.95f);
        }
        this.pos.add(vel);
        System.out.println("Debug: Velx Spaceship: " + vel.x +" Vely Spaceship: " + vel.y);
        this.wPressed = false;
    }
}
