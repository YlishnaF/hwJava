package org.example.lesson1.example;

public class RobotMapFactory {
    public RobotMapInterface create(int x, int y) throws RobotMapCreationException {
        return new RobotMap(x, y);
    }
}
