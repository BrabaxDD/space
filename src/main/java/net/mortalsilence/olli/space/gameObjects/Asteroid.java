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
    private float xDeplacement;
    private float yDeplacement;
    private final int size;

    private PVector speed;

    private PVector pos;


    public Asteroid(int pointsPerQuarter, int sizeH, int xdep, int ydep, Scene scene) {
        super(scene);
        this.xDeplacement = xdep;
        this.yDeplacement = ydep;
        this.size = sizeH;
        this.points = new ArrayList<PVector>();
        this.pos = new PVector();
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
                    this.pos.x = AsteroidsApplet.asteroidsApplet.random(xBorder.x, xBorder.y) * this.size;
                    this.pos.y = AsteroidsApplet.asteroidsApplet.random(yBorder.x, yBorder.y) * this.size;
                } while (dist(0, 0, this.pos.x, this.pos.y) > this.size || dist(0, 0, this.pos.x, this.pos.y) < (float) this.size / 2);

                cache.add(new PVector(this.pos.x, this.pos.y, PApplet.atan2( this.pos.y,this.pos.x)));
            }

            cache.sort( Comparator.comparing(v -> v.z));
            Collections.reverse(cache);
            println("cache: " + cache);
            points.addAll(cache);
            PApplet.println("points: "+points);

        }
        for (PVector i : points) {
            i.add(new PVector(this.xDeplacement, this.yDeplacement));
        }
    }
    public void render(boolean linesOn, boolean hitBoxOn, boolean cornersOn) {

        AsteroidsApplet.asteroidsApplet.fill(255,255,255);
        AsteroidsApplet.asteroidsApplet.stroke(255,255,255);
        if (hitBoxOn) {
            AsteroidsApplet.asteroidsApplet.fill(150);
            AsteroidsApplet.asteroidsApplet.circle(this.xDeplacement, this.yDeplacement, this.size*2);
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

    public void process() {
        for (PVector i : this.points) {
            i.add(this.speed);
        }
        this.xDeplacement += this.speed.x;
        this.yDeplacement += this.speed.y;
    }
}

