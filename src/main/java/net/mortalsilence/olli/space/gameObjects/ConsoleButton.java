package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.MouseLeftClickListener;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PConstants;
import processing.core.PVector;

import javax.swing.*;
import java.awt.*;
import java.util.EventListener;

public class ConsoleButton extends Button implements MouseLeftClickListener {
    private Scene scene;

    private PVector pos;

    private PVector diagonal;

    private String entered;

    public ConsoleButton(Scene scene, PVector pos, PVector diagonal) {
        super(scene, pos, diagonal);
        this.scene = scene;
        this.pos = pos;
        this.diagonal = diagonal;
    }

    @Override
    public void render(){
        super.render();
        AsteroidsApplet.asteroidsApplet.textAlign(PConstants.CENTER);
        AsteroidsApplet.asteroidsApplet.fill(0);
        AsteroidsApplet.asteroidsApplet.text("Console", this.pos.x+this.diagonal.x/2, this.pos.y+this.diagonal.y/2 + 15);
    }

    @Override
    public void buttonPressed(){
        this.entered = JOptionPane.showInputDialog("Console: ");
    }

    @Override
    public void process(){
        boolean isCommand = false;
        if(this.entered != null) {
            if (this.entered.equals("$et Game Rule invincible to on")) {
                AsteroidsApplet.asteroidsApplet.setGameRuleBoolean(10, true);
                isCommand = true;
            }
            if (this.entered.equals("$et Game Rule invincible to off")) {
                AsteroidsApplet.asteroidsApplet.setGameRuleBoolean(10, false);
                isCommand = true;
            }
            if(this.entered.equals("$et Game Rule debug to on")){
                AsteroidsApplet.asteroidsApplet.setGameRuleBoolean(3, true);
                isCommand = true;
            }
            if(this.entered.equals("$et Game Rule debug to off")){
                AsteroidsApplet.asteroidsApplet.setGameRuleBoolean(3, false);
                isCommand = true;
            }
            if(this.entered.equals("$et global volume")){
                float x = Float.parseFloat(JOptionPane.showInputDialog("Set global Volume: (0-100)"));
                AsteroidsApplet.asteroidsApplet.setVolumeGlobal(x);
                isCommand = true;
            }
            if(isCommand){
                JOptionPane.showMessageDialog(null, "Confirmed");
            }else if(!this.entered.isEmpty()){
                JOptionPane.showMessageDialog(null, "That is no command :(");
            }
        }

        this.entered = "";
    }
}
