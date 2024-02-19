package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PImage;
import processing.core.PVector;

public class ShieldItem extends Item{
    private int lifespan;

    private PImage texture;
    private PVector pos;
    public ShieldItem(Scene scene, PVector pos, int lifespan) {
        super(scene,1, pos, lifespan);
        this.lifespan = lifespan;
        this.pos = pos;
        //texture = AsteroidsApplet.asteroidsApplet.loadImage();
    }
    
    @Override
    public void render(){
        if(this.lifespan > 7000){
            AsteroidsApplet.asteroidsApplet.fill(255,255,0);
        } else if (this.lifespan > 5000) {
            AsteroidsApplet.asteroidsApplet.fill(255,0,255);
        } else if (this.lifespan > 2000) {
            AsteroidsApplet.asteroidsApplet.fill(144,250,0);
        } else if (this.lifespan > 500) {
            AsteroidsApplet.asteroidsApplet.fill(150,90,42);
        }
        super.render();

        //AsteroidsApplet.asteroidsApplet.image(texture, pos.x, pos.y);
    }
}

