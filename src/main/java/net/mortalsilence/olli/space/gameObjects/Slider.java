package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.MouseLeftClickListener;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PApplet;
import processing.core.PVector;

import static processing.core.PApplet.constrain;

public class Slider extends GameObject implements MouseLeftClickListener {
    private PVector pos;

    private PVector diagonal;

    private float valueShown;

    private float value;

    private PVector mouseClicked;

    private float valueCap;

    private String text;
    public Slider(PVector pos, PVector diagonal, float value ,float cap, String textToDisplay , Scene scene){
        super(scene);
        this.pos = pos;
        this.diagonal = diagonal;
        this.valueCap = cap;
        this.value = value;
        this.text = textToDisplay;
        this.scene.getEventbus().registerEventMouseLeftClick(this);
    }

    @Override
    public void mouseLeftClick(int x, int y) {
        this.mouseClicked = new PVector(x,y);
        //AsteroidsApplet.asteroidsApplet.circle(200,200,50);
        if(AsteroidsApplet.asteroidsApplet.mouseX > this.pos.x && AsteroidsApplet.asteroidsApplet.mouseX < this.pos.x + this.diagonal.x && AsteroidsApplet.asteroidsApplet.mouseY > this.pos.y && AsteroidsApplet.asteroidsApplet.mouseY < this.pos.y + this.diagonal.y){
            this.buttonPressed();
            System.out.println("Value:"+value);
        }

    }
    @Override
    public void render(){
        //großes Rechteck
        AsteroidsApplet.asteroidsApplet.noStroke();
        AsteroidsApplet.asteroidsApplet.fill(0,0,255);
        AsteroidsApplet.asteroidsApplet.rect(this.pos.x,this.pos.y,this.diagonal.x,this.diagonal.y);

        //kleines Rechteck
        AsteroidsApplet.asteroidsApplet.stroke(0);
        AsteroidsApplet.asteroidsApplet.fill(255,255,255);
        AsteroidsApplet.asteroidsApplet.rect(this.pos.x+10,this.pos.y+12,this.diagonal.x-20,this.diagonal.y-25);

        //kleines Quadrat
        AsteroidsApplet.asteroidsApplet.fill(155,155,155);
        AsteroidsApplet.asteroidsApplet.rect(this.pos.x + this.diagonal.x/this.valueCap*value-10,this.pos.y,20,this.diagonal.y);

        AsteroidsApplet.asteroidsApplet.fill(0);
        AsteroidsApplet.asteroidsApplet.text(this.text, this.pos.x + this.diagonal.x/2,this.pos.y + this.diagonal.y-12);

    }

    public void buttonPressed(){
        this.value = constrain(PApplet.map(mouseClicked.x, this.pos.x, this.pos.x + this.diagonal.x, 0, valueCap), 0,valueCap);
        setValue(this.value);
    }

    public float getValue(){return this.value;}

    public void setValue(float value){}

    @Override
    public void process(){}
}
