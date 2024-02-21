package net.mortalsilence.olli.space.events;

import net.mortalsilence.olli.space.gameObjects.AlienUFO;
import net.mortalsilence.olli.space.gameObjects.Asteroid;
import net.mortalsilence.olli.space.gameObjects.GameObject;
import net.mortalsilence.olli.space.gameObjects.Spaceship;

public interface AlienUFOMovedListener {
        public void alienUFOMoved(AlienUFO ufo);
}
