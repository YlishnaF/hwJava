package org.example.lesson2.game;

public class ThrowObstacleAdapter implements Obstacle{
    private final Throw aThrow;

    public ThrowObstacleAdapter(Throw aThrow) {
        this.aThrow = aThrow;
    }

    @Override
    public boolean overcome(Participant participant) {
        return participant.getThrowingLimit()>=aThrow.getDist();
    }

    @Override
    public String type() {
        return "Бросок";
    }
}
