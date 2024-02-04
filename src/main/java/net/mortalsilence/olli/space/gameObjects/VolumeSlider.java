package net.mortalsilence.olli.space.gameObjects;

import jdk.jfr.Percentage;
import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PApplet;
import processing.core.PVector;

import static processing.core.PApplet.str;

public class VolumeSlider extends Slider{

    private PVector pos;

    private PVector diagonal;

    private float value;

    private float valueCap;
    private float amountP;

    private boolean type;
    private float p;
    //type: 0 ==> background;     1 ==> fx
    public VolumeSlider(PVector pos, PVector diagonal, float value, float cap,String text, Scene scene, boolean type) {
        super(pos, diagonal, value, cap, text,scene);
        this.pos = pos;
        this.diagonal = diagonal;
        this.valueCap = cap;
        this.value = value;
        amountP = valueCap/diagonal.x;
        //PApplet.println("amountP: "+amountP);
        this.type = type;
    }


    @Override
    public void process(){
        p = this.getValue();
        amountP = p/diagonal.x*4;
        //PApplet.println("amountP: "+amountP);
        if(!this.type) {
           AsteroidsApplet.asteroidsApplet.getBackgroundPlayer().setVolume(amountP);
        }else AsteroidsApplet.asteroidsApplet.getFxPlayer().setVolume(amountP);
    }


    @Override
    public void render(){
        super.render();


    }

    @Override
    public void mouseLeftClick(int x, int y) {
        super.mouseLeftClick(x, y);
        p = this.getValue();
        amountP = valueCap/diagonal.x*p;
        // Writes the remaining data to the file
        if (!this.type) {
            AsteroidsApplet.asteroidsApplet.output = AsteroidsApplet.asteroidsApplet.createWriter("src/main/java/net/mortalsilence/olli/space/options.txt");
            String cache = str(this.p);
            AsteroidsApplet.asteroidsApplet.output.write(cache);
        }else{
            AsteroidsApplet.asteroidsApplet.output = AsteroidsApplet.asteroidsApplet.createWriter("src/main/java/net/mortalsilence/olli/space/optionsfx.txt");
        String cache = str(this.p);
        AsteroidsApplet.asteroidsApplet.output.write(cache);
        }
        AsteroidsApplet.asteroidsApplet.output.flush();  // Writes the remaining data to the file
        AsteroidsApplet.asteroidsApplet.output.close();
    }
}
