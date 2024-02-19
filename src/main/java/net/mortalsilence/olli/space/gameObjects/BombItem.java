package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.scenes.Scene;
import processing.core.PVector;

public class BombItem extends Item{

    public BombItem(Scene scene, PVector pos, int lifespan){
        super(scene,2, pos, lifespan);

    }
}
