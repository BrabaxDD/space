package net.mortalsilence.olli.space.utility;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.gameObjects.*;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class SweepAndPrune {
    List<Interval> intervalsX;


    List<Interval> intervalToDelete = new ArrayList<>();

    ArrayList<GameObject> objects;

    PApplet parent;
    private ArrayList<GameObject> toDelete = new ArrayList<>();
    private ArrayList<GameObject> toAdd = new ArrayList<>();

    public SweepAndPrune(PApplet p) {
        parent = p;
        intervalsX = new ArrayList<>();
        this.objects = new ArrayList<>();
    }

    public void addObject(GameObject obj) {
        objects.add(obj);

    }

    public void removeObject(GameObject obj) {

        intervalsX.removeIf(interval -> interval.obj == obj);

        objects.remove(obj);
    }

    public void process(){



        /*if(this.toDelete != null){
            for(GameObject object : this.toDelete){
                this.objects.remove(object);
            }
            this.toDelete = new ArrayList<>();
        }
        if(this.toAdd != null){
            this.objects.addAll(this.toAdd);
            this.toAdd = new ArrayList<>();
        }
        if(this.intervalToDelete != null){
            for(Interval interval: this.intervalToDelete){
                this.intervalsX.remove(interval);
            }
            this.toDelete = new ArrayList<>();
        }*/
        intervalsX = new ArrayList<>();
        for (GameObject object : objects) {

            float startX = object.getPos().x - object.getSize();
            float endX = object.getPos().x + object.getSize();

            intervalsX.add(new Interval(startX, endX, object));
        }

        sweep();
    }

    public void sweep() {
        intervalsX.sort(new IntervalComparator());

        //Objekte werden auf x-Achse projezierrt

        // Sweep X dimension
        for (int i = 0; i < intervalsX.size(); i++) {
            Interval intervalA = intervalsX.get(i);
            for (int j = i + 1; j < intervalsX.size(); j++) {
                Interval intervalB = intervalsX.get(j);
                /*if(intervalA.obj.getClass() == Projektile.class ||intervalB.obj.getClass() == Projektile.class){

                    System.out.println("Objekt 1: "+intervalA.obj + "   Objekt 2: "+intervalB.obj);
                }*/
                // Wenn Intervall A aufhört, bevor Intervall B anfängt, aufhören
                if (intervalA.end < intervalB.start)
                    break;
                // Otherwise, check for overlap
                if (intervalA.end >= intervalB.start && intervalA.start <= intervalB.end) {
                    Class<? extends GameObject> classA = intervalA.obj.getClass();
                    Class<? extends GameObject> classB = intervalB.obj.getClass();
                    // Auf x-Achse überschneiden

                    if(abs(intervalA.obj.getPos().y - (intervalB.obj.getPos().y)) < intervalB.obj.getSize()+ intervalA.obj.getSize() ){
                        //Wenn Asteroid und Raumschiff zusammenstoßen
                        if(classB == Asteroid.class && classA == Spaceship.class){
                            AsteroidsApplet.asteroidsApplet.getActiveScene().getEventbus().astroidMoved((Asteroid) intervalB.obj);
                            break;
                        }
                        else if(classA == Asteroid.class && classB == Spaceship.class){
                            AsteroidsApplet.asteroidsApplet.getActiveScene().getEventbus().astroidMoved((Asteroid) intervalA.obj);
                            break;
                        }

                        //Wenn AlienUFO und Raumschiff zusammenstoßen
                        if(classB == AlienUFO.class && classA == Spaceship.class){
                            AsteroidsApplet.asteroidsApplet.getActiveScene().getEventbus().alienUFOMoved((AlienUFO) intervalB.obj);
                            break;
                        }
                        if(classA == AlienUFO.class && classB == Spaceship.class){
                            AsteroidsApplet.asteroidsApplet.getActiveScene().getEventbus().alienUFOMoved((AlienUFO) intervalA.obj);
                            break;
                        }

                        //Bei Projektilzusammenstoß


                        if(classB == Projektile.class){
                            Projektile p = (Projektile) intervalB.obj;
                            if(p.getShooter() != intervalA.obj){
                                AsteroidsApplet.asteroidsApplet.getActiveScene().getEventbus().spaceshipProjektileMoved((Projektile)intervalB.obj,  (GameObject)  p.getShooter(), intervalA.obj);
                            }
                            break;
                        }
                        if(classA == Projektile.class){
                            Projektile p = (Projektile) intervalA.obj;
                            if(p.getShooter() != intervalB.obj) {
                                AsteroidsApplet.asteroidsApplet.getActiveScene().getEventbus().spaceshipProjektileMoved((Projektile) intervalA.obj, (GameObject) p.getShooter(), intervalB.obj);
                            }
                            break;
                        }
                    }



                }
            }
        }
    }
}