package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static processing.core.PApplet.*;

public class Asteroid extends GameObject {
    private final ArrayList<PVector> points;
    private PVector deplacement;
    private final int size;

    private PVector speed = new PVector(0,0);

    private PVector pos = new PVector();

    final private boolean linesOn = true;

    final private  boolean  hitBoxOn = false;

    final private boolean cornersOn = false;

    final private int pointsPQuarter;

    public Asteroid(int pointsPerQuarter, int sizeH, int xdep, int ydep, Scene scene) {
        super(scene);
        this.pointsPQuarter = pointsPerQuarter;
        this.pos.x = xdep;
        this.pos.y = ydep;
        this.size = sizeH;
        this.points = new ArrayList<PVector>();
        this.deplacement = new PVector();
        this.speed = new PVector(0,0);
        PVector xBorder = new PVector(0, 1);
        PVector yBorder = new PVector(-1, 0);



        for (int cycle = 0; cycle < 4; cycle++) {


            ArrayList<PVector> cache = new ArrayList<>();
            switch (cycle) {

                case 0:
                    points.add(new PVector(this.size, 0,PApplet.atan2(0, this.size)));
                    println(atan2(-0, this.size-50));
                    break;

                case 1:
                    xBorder = new PVector(-1, 0);
                    yBorder = new PVector(-1, 0);
                    points.add(new PVector(0, -this.size,PApplet.atan2(-this.size, 0)));
                    break;

                case 2:
                    xBorder = new PVector(-1, 0);
                    yBorder = new PVector(0, 1);
                    points.add(new PVector(-this.size, 0,PApplet.atan2(0, -this.size)));
                    break;

                case 3:
                    xBorder = new PVector(0, 1);
                    yBorder = new PVector(0, 1);
                    points.add(new PVector(0, this.size,PApplet.atan2(this.size, 0)));
                    break;
            }
            for (int i = 0; i < pointsPerQuarter; i++) {
                do {
                    this.deplacement.x = AsteroidsApplet.asteroidsApplet.random(xBorder.x, xBorder.y) * this.size;
                    this.deplacement.y = AsteroidsApplet.asteroidsApplet.random(yBorder.x, yBorder.y) * this.size;
                } while (dist(0, 0, this.deplacement.x, this.deplacement.y) > this.size || dist(0, 0, this.deplacement.x, this.deplacement.y) < (float) this.size / 2);

                cache.add(new PVector(this.deplacement.x, this.deplacement.y, PApplet.atan2( this.deplacement.y,this.deplacement.x)));
            }

            cache.sort( Comparator.comparing(v -> v.z));
            Collections.reverse(cache);
            println("cache: " + cache);
            points.addAll(cache);
            PApplet.println("points: "+points);

        }
        for (PVector i : points) {
            i.add(new PVector(this.pos.x, this.pos.y));
        }
    }
    public void render() {

        AsteroidsApplet.asteroidsApplet.fill(255,255,255);
        AsteroidsApplet.asteroidsApplet.stroke(255,255,255);
        if (hitBoxOn) {
            AsteroidsApplet.asteroidsApplet.fill(150);
            AsteroidsApplet.asteroidsApplet.circle(this.pos.x, this.pos.y, this.size*2);
        }

        if (cornersOn) {
            for(PVector i : this.points) {
                AsteroidsApplet.asteroidsApplet.circle(i.x, i.y, 10);
            }
        }

        if (linesOn) {
            for (int i = 0; i < points.size()-1; i++) {
                AsteroidsApplet.asteroidsApplet.line(this.points.get(i).x, this.points.get(i).y, this.points.get(i+1).x, this.points.get(i+1).y);
            }
            AsteroidsApplet.asteroidsApplet.line(this.points.get(this.points.size()-1).x, this.points.get(points.size()-1).y, this.points.get(0).x, this.points.get(0).y);
        }
    }

    public void setSpeed(PVector speed) {
        this.speed = speed;
    }

    public void setPos(PVector pos) {
        this.pos = pos;
    }

    @Override
    public void process() {
        for (PVector i : this.points) {
            i.add(this.speed);
        }
        this.pos.x += this.speed.x;
        this.pos.y += this.speed.y;
    }

    private void asteroidHit() {
        this.scene.deleteObject(this);
        if(this.size > 20) {
            this.scene.addObject(new Asteroid(this.pointsPQuarter,3,3,3,scene));

        }
    }
}

