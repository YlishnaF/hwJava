package org.example.lesson1.example;

public interface RobotInterface {
    void move(int n) throws RobotMoveException;
    void changeDirection(Direction newDirection);
    Long getId();

}
