package net.mortalsilence.olli.space.gameObjects.GUI;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.MouseLeftClickListener;
import net.mortalsilence.olli.space.gameObjects.GameObject;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PVector;

public class Button extends GameObject implements MouseLeftClickListener {

    protected PVector posLeftUp;
    protected PVector diagonal;
    public Button(Scene scene,PVector pos,PVector diagonal) {
        super(scene);
        this.posLeftUp = pos;
        this.diagonal = diagonal;
    }



    @Override
    public void mouseLeftClick(int x, int y) {
        if(AsteroidsApplet.asteroidsApplet.mouseX > this.posLeftUp.x && AsteroidsApplet.asteroidsApplet.mouseX < this.posLeftUp.x + this.diagonal.x && AsteroidsApplet.asteroidsApplet.mouseY > this.posLeftUp.y && AsteroidsApplet.asteroidsApplet.mouseY < this.posLeftUp.y + this.diagonal.y){
            AsteroidsApplet.asteroidsApplet.getFxPlayer().playSound(4);
            this.buttonPressed();
        }
    }
    @Override
    public void render(){
        if(AsteroidsApplet.asteroidsApplet.mouseX > this.posLeftUp.x && AsteroidsApplet.asteroidsApplet.mouseX < this.posLeftUp.x + this.diagonal.x && AsteroidsApplet.asteroidsApplet.mouseY > this.posLeftUp.y && AsteroidsApplet.asteroidsApplet.mouseY < this.posLeftUp.y + this.diagonal.y){
            AsteroidsApplet.asteroidsApplet.fill(50);
            AsteroidsApplet.asteroidsApplet.rect(posLeftUp.x -10,posLeftUp.y-10,diagonal.x+20,diagonal.y+20);
        }
        AsteroidsApplet.asteroidsApplet.noStroke();
        AsteroidsApplet.asteroidsApplet.fill(0,0,255);
        AsteroidsApplet.asteroidsApplet.rect(posLeftUp.x,posLeftUp.y,diagonal.x,diagonal.y);

    }
    protected void buttonPressed(){}

    @Override
    public PVector getPos(){
        return this.posLeftUp;
    }


}
