package org.example.lesson2.game;

public class Road extends AbstractObstacle{

    public Road(int distance) {
        super(distance);
    }

    public boolean overcome(Runnable runnable) {
        int distanceLimit = runnable.getDistanceLimit();
        return distanceLimit >= super.getDist();
    }
}
