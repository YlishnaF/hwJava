package org.example.lesson2.game;

public abstract class Participant implements Runnable, Jumpable, Swimmable, Throwable {

    private final String name;
    private final int distanceLimit;
    private final int swimLimit;
    private final int jumpLimit;
    private final int throwLimit;

    public Participant(String name, int distanceLimit, int swimLimit, int jumpLimit, int throwLimit) {
        this.name = name;
        this.distanceLimit = distanceLimit;
        this.swimLimit = swimLimit;
        this.jumpLimit = jumpLimit;
        this.throwLimit=throwLimit;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getDistanceLimit() {
        return distanceLimit;
    }

    @Override
    public int getSwimLimit() {
        return swimLimit;
    }

    @Override
    public int getJumpLimit() {
        return jumpLimit;
    }

    @Override
    public int getThrowingLimit() {
        return throwLimit;
    }
}
