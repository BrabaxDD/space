package net.mortalsilence.olli.space.utility;

import net.mortalsilence.olli.space.AsteroidsApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Particle_effect {
    private ArrayList<Particle> particles;

    public Particle_effect(PVector pos){
        particles = new ArrayList<Particle>();
        for(int i = 0; i<10; i++){
            addParticle(new Particle(pos));
        }
    }

    private void addParticle(Particle part){
        this.particles.add(part);
    }

    public void run(){
        ArrayList<Particle> toDelete = new ArrayList<Particle>();
        for(Particle part : this.particles){
            part.run();
            part.render();
            if(part.getLife() <= 0){
                toDelete.add(part);
            }
        }
        this.particles.removeAll(toDelete);
        if(particles.size() <= 0){
            AsteroidsApplet.asteroidsApplet.deleteEffect(this);
        }
    }
}
