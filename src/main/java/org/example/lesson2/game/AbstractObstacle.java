package org.example.lesson2.game;

public abstract class AbstractObstacle {
    private int dist;

    public AbstractObstacle(int dist) {
        this.dist = dist;
    }

    public int getDist() {
        return dist;
    }
}
