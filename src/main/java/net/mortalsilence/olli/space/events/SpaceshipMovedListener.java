package net.mortalsilence.olli.space.events;

import net.mortalsilence.olli.space.gameObjects.Projektile;
import net.mortalsilence.olli.space.gameObjects.Spaceship;
import processing.core.PVector;

public interface SpaceshipMovedListener {
    public void spaceshipMoved(PVector pos);
}
