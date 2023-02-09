package org.example.lesson1.example;

import java.util.*;

public class RobotMap implements RobotMapInterface{
//    public List<Robot> getRobots() {
//        return robots;
//    }

    private final int n;
    private final int m;
    private final List<Robot> robots;

    public RobotMap(int n, int m) throws RobotMapCreationException {
        if (n < 0 || m < 0) {
            throw new RobotMapCreationException("Некоректный размер карты");
        }

        this.n = n;
        this.m = m;
        this.robots = new ArrayList<>();
    }

    public Robot createRobot(Point point) throws RobotCreationException {
        final MapPoint robotPosition;
        try {
            validatePoint(point);
            robotPosition = new MapPoint(point.getX(), point.getY());
        } catch (PointValidationException e) {
            throw new RobotCreationException(e.getMessage());
        }

        Robot robot = new Robot(robotPosition);
        robots.add(robot);
        return robot;
    }

    @Override
    public String info() {
        List<String> robotsInfo = new ArrayList<>();
        for (Robot r : robots) {
            robotsInfo.add("id: " + r.getId() + ", point: " + r.getPoint());
        }
        return robotsInfo.toString();
    }

    @Override
    public String changeDirection(Long id, String s) {
        RobotInterface r = getRobot(id);
            if (r.getId() == id) {
                switch (s) {
                    case "t":
                        r.changeDirection(Direction.TOP);
                        return "Direction TOP";
                    case "b":
                        r.changeDirection(Direction.BOTTOM);
                        return "Direction Bottom";
                    case "l":
                        r.changeDirection(Direction.LEFT);
                        return "Direction Left";
                    case "r":
                        r.changeDirection(Direction.RIGHT);
                        return "Direction Right";
                    default:
                        return "Direction not changed";
                }

        }
        return "";
    }

    @Override
    public RobotInterface getRobot(Long id) {
        for (RobotInterface r: robots) {
            if(Objects.equals(r.getId(), id)){
                return r;
            };
        }
        return null;
    }

    @Override
    public void move(Long id, int n) throws RobotMoveException {
        RobotInterface r = getRobot(id);
        r.move(n);
    }


    private void validatePoint(Point point) throws PointValidationException {
        validatePointIsFree(point);
    }

    private void validatePointIsFree(Point point) throws PointValidationException {
        for (Robot robot : robots) {
            if (point.equals(robot.getPoint())) {
                throw new PointValidationException("Позиция " + point + " занята другим роботом: " + robot);
            }
        }
    }

    public class Robot implements RobotInterface{

        public static final Direction DEFAULT_DIRECTION = Direction.TOP;

        private static Long idSequence = 1L;

        private final Long id;
        private MapPoint point;
        private Direction direction;

        public Direction getDirection() {
            return direction;
        }

        public Robot(MapPoint point) {
            this.id = idSequence++; //UUID.randomUUID();
            this.point = point;
            this.direction = DEFAULT_DIRECTION;
        }

        public Long getId() {
            return id;
        }

        public void move() throws RobotMoveException {
            final MapPoint newPoint;
            try {
                newPoint = switch (direction) {
                    case TOP -> new MapPoint(point.getX() - 1, point.getY());
                    case RIGHT -> new MapPoint(point.getX(), point.getY() + 1);
                    case BOTTOM -> new MapPoint(point.getX() + 1, point.getY());
                    case LEFT -> new MapPoint(point.getX(), point.getY() - 1);
                };

                validatePoint(newPoint);
            } catch (PointValidationException e) {
                throw new RobotMoveException(e.getMessage(), this);
            }

            this.point = newPoint;
        }

        public void move(int n) throws RobotMoveException {
            final MapPoint newPoint;
            try {
                newPoint = switch (direction) {
                    case TOP -> new MapPoint(point.getX() - n, point.getY());
                    case RIGHT -> new MapPoint(point.getX(), point.getY() + n);
                    case BOTTOM -> new MapPoint(point.getX() + n, point.getY());
                    case LEFT -> new MapPoint(point.getX(), point.getY() - n);
                };

                validatePoint(newPoint);
            } catch (PointValidationException e) {
                throw new RobotMoveException(e.getMessage(), this);
            }

            this.point = newPoint;
            System.out.println(direction.toString());
        }

        public void changeDirection(Direction direction) {
            this.direction = direction;
        }

        public MapPoint getPoint() {
            return point;
        }

        @Override
        public String toString() {
            return "Robot-" + id + point;
        }
    }

    public class MapPoint extends Point {

        public MapPoint(int x, int y) throws PointValidationException {
            super(x, y);

            if (x < 0 || x > n || y < 0 || y > m) {
                throw new PointValidationException("Недопустимое значение Point: " + this);
            }
        }
    }

}
