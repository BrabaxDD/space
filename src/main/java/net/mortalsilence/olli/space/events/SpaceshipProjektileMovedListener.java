package net.mortalsilence.olli.space.events;

import net.mortalsilence.olli.space.gameObjects.Projektile;
import processing.core.PVector;

public interface SpaceshipProjektileMovedListener {
    public void spaceshipProjektileMoved(PVector pos, Projektile projektile);

}
