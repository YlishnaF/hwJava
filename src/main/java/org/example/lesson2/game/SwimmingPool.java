package org.example.lesson2.game;

public class SwimmingPool extends AbstractObstacle {

    public SwimmingPool(int distance) {
        super(distance);
    }

    public boolean overcome(Participant swimmable) {
        int swimLimit = swimmable.getSwimLimit();
        return swimLimit>=super.getDist();
    }
}
