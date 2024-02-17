package net.mortalsilence.olli.space.events;

import net.mortalsilence.olli.space.gameObjects.Item;
import processing.core.PVector;

public interface ItemPickedUpListener {
    public void itemPickedUp(Item item);
}
