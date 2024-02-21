package net.mortalsilence.olli.space.utility;

import net.mortalsilence.olli.space.gameObjects.GameObject;

class Interval {
    float start;
    float end;
    GameObject obj;

    Interval(float start, float end, GameObject obj) {
        this.start = start;
        this.end = end;
        this.obj = obj;
    }
}