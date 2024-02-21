package net.mortalsilence.olli.space.events;

import net.mortalsilence.olli.space.gameObjects.GameObject;
import net.mortalsilence.olli.space.gameObjects.Projektile;
import processing.core.PVector;

public interface SpaceshipProjektileMovedListener {
    public void spaceshipProjektileMoved(Projektile projektile,GameObject shooter, GameObject target);

}
