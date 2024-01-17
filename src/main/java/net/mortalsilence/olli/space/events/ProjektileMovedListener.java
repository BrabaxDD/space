package net.mortalsilence.olli.space.events;

import net.mortalsilence.olli.space.gameObjects.Projektile;

public interface ProjektileMovedListener {
    public void ProjektileMoved(int posx, int posy, Projektile projektile);
}
