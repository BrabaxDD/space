package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

import static processing.core.PApplet.dist;

public class Asteroid extends GameObject {
    PVector velo;
    private ArrayList<PVector> points;
    PVector test;

    private float xDeplacement;
    private float yDeplacement;
    private final int size;


    public Asteroid(int pointsPerQuarter, int sizeH, int posx, int posy,Scene scene){
        super(scene);
        this.xDeplacement = posx;
        this.yDeplacement = posy;
        this.size = sizeH;
        this.points = new ArrayList<PVector>();
        float x = 0;
        float y = 0;
        PVector xBorder = new PVector(0, 1);
        PVector yBorder = new PVector(-1, 0);

        for (int cycle = 0; cycle<4; cycle++) {

            switch(cycle) {

                case 0 :
                    points.add(new PVector(size, 0));
                    break;

                case 1:
                    xBorder = new PVector(-1, 0);
                    yBorder = new PVector(-1, 0);
                    points.add(new PVector(0, -size));
                    break;

                case 2:
                    xBorder = new PVector(-1, 0);
                    yBorder = new PVector(0, 1);
                    points.add(new PVector(-size, 0));
                    break;

                case 3:
                    xBorder = new PVector(0, 1);
                    yBorder = new PVector(0, 1);
                    points.add(new PVector(0, size));
                    break;
            }
            for (int i = 0; i<pointsPerQuarter; i++) {
                do {
                    x = AsteroidsApplet.asteroidsApplet.random(xBorder.x, xBorder.y) * this.size;
                    y = AsteroidsApplet.asteroidsApplet.random(yBorder.x, yBorder.y) * this.size;
                } while (dist(0, 0, x, y)>this.size || dist(0, 0, x, y)<this.size/10);
                points.add(new PVector(x, y));
            }

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
            for(int i = 0; i < this.points.size(); i++) {
                AsteroidsApplet.asteroidsApplet.circle(this.points.get(i).x, this.points.get(i).y, 10);
            }
        }

        if (linesOn) {
            for (int i = 0; i < points.size()-1; i++) {
                AsteroidsApplet.asteroidsApplet.line(this.points.get(i).x, this.points.get(i).y, this.points.get(i+1).x, this.points.get(i+1).y);
            }
            AsteroidsApplet.asteroidsApplet.line(this.points.get(this.points.size()-1).x, this.points.get(points.size()-1).y, this.points.get(0).x, this.points.get(0).y);
        }
    }

    public void process(PVector speed) {
        for (PVector i : this.points) {
            i.add(speed);
        }
        this.xDeplacement += speed.x;
        this.yDeplacement += speed.y;
    }
}

