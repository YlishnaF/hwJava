package org.example.lesson2.game;

public class RoadObstacleAdapter implements Obstacle{
    private final Road road;

    public RoadObstacleAdapter(Road road) {
        this.road = road;
    }

    @Override
    public boolean overcome(Participant participant) {
        return road.overcome(participant);
    }

    @Override
    public String type() {
        return "Беговая дорожка";
    }
}
