package org.example.lesson2.game;

public class Throw extends AbstractObstacle{

    public Throw(int dist) {
        super(dist);
    }
    public boolean overcome(Throwable throwable) {
        int throwLimit = throwable.getThrowingLimit();
        return throwLimit >= super.getDist();
    }
}
