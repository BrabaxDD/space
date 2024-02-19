package net.mortalsilence.olli.space.gameObjects.GUI;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.MouseWheelListener;
import net.mortalsilence.olli.space.gameObjects.GameObject;
import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PConstants;

public class ScrollText extends GameObject implements MouseWheelListener {

    private Scene scene;

    private String[] text;

    private int scroll = 0;

    private int textSize;
    public ScrollText(Scene scene, String[] text, int textSize) {
        super(scene);
        this.scene = scene;
        this.text = text;
        this.textSize = textSize;
        this.scene.getEventbus().registerEventMouseWheeled(this);
    }


    @Override
    public void render(){
        AsteroidsApplet.asteroidsApplet.fill(255);
        AsteroidsApplet.asteroidsApplet.textAlign(PConstants.LEFT);
        AsteroidsApplet.asteroidsApplet.textSize(this.textSize);

        for(int i = 0; i<text.length; i++){
            AsteroidsApplet.asteroidsApplet.text(text[i], (float) AsteroidsApplet.asteroidsApplet.width /10, (float) AsteroidsApplet.asteroidsApplet.height /20+scroll+i*textSize);
        }
    }

    @Override
    public void mouseWheeled(int y) {
        if(AsteroidsApplet.asteroidsApplet.getActiveScene() == scene) {
            this.scroll -= 20 * y;
        }
    }
}
