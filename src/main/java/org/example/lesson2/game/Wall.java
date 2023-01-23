package org.example.lesson2.game;

public class Wall extends AbstractObstacle{

    public Wall(int height) {
        super(height);
    }

    public boolean overcome(Jumpable jumpable) {
        int jumpLimit = jumpable.getJumpLimit();
        return jumpLimit >= super.getDist();
    }
}
