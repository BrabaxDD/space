package net.mortalsilence.olli.space.utility;

import net.mortalsilence.olli.space.AsteroidsApplet;
import processing.core.PVector;

public class Particle {
    private float size;
    private PVector dir;
    private int life;
    private PVector pos;
    private float shrinkage;

    public Particle(PVector nullPos){
        this.size = AsteroidsApplet.asteroidsApplet.random(6,20);
        this.dir = new PVector(AsteroidsApplet.asteroidsApplet.random(-1,1), AsteroidsApplet.asteroidsApplet.random(-1,1));
        this.pos = new PVector(nullPos.x, nullPos.y);//new PVector(500,500);
        this.life = (int) AsteroidsApplet.asteroidsApplet.random(30,60);
        this.shrinkage = this.size/this.life;
    }

    public void run(){
        this.pos.add(new PVector(this.dir.x*3, this.dir.y*3));
        this.life--;
        this.size -= this.shrinkage;
    }

    public int getLife(){
        return this.life;
    }

    public void render(){
        AsteroidsApplet.asteroidsApplet.fill(255);
        AsteroidsApplet.asteroidsApplet.noStroke();
        AsteroidsApplet.asteroidsApplet.circle(this.pos.x, this.pos.y, this.size);
        AsteroidsApplet.asteroidsApplet.stroke(255);
    }
}
