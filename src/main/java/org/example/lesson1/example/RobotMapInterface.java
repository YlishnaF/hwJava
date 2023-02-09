package org.example.lesson1.example;

import java.util.List;

public interface RobotMapInterface {
    RobotInterface createRobot(Point point) throws RobotCreationException;
    String info();
    String changeDirection(Long id, String s);
    RobotInterface getRobot(Long id);
    void move(Long id, int n) throws RobotMoveException;
}
