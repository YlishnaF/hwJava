package org.example.lesson7.hw;

public class Main {

    public static void main(String[] args) {
        // Есть машина, которая умеет делать круглые отверстия для круглых колышков (RoundPeg).
        // Также у нас есть квадратные колышки (SquareSide).
        // Задача: научить машину делать отверстия для квадратных колышков (реализовать паттерн адаптер).
        // Нужно "адаптировать" интерфейс SquarePeg под интерфейс RoundPeg

        RoundPegHoleMachine machine = new RoundPegHoleMachine();

//        RoundPeg peg = new DefaultRoundPeg(2.0);
//        RoundHole hole = machine.create(peg);
//        System.out.println(hole.radius()); // 2.0

        RoundHoleAdapter roundHoleAdapter = new RoundHoleAdapter(5); // new ...
        System.out.println(((SquarePeg) roundHoleAdapter).side()); // 5.0
        RoundHole squareHole = machine.create((RoundPeg) roundHoleAdapter);
        System.out.println(squareHole.radius()); // ~ 3.5355
        // side -> radius
    }

    static class RoundPegHoleMachine {
        public RoundHole create(RoundPeg peg) {
            return new DefaultRoundHole(peg.radius());
        }
    }

    interface RoundHole {
        double radius();
    }

    static class DefaultRoundHole implements RoundHole {
        private final double radius;
        public DefaultRoundHole(double radius) {
            this.radius = radius;
        }
        public double radius() {
            return radius;
        }
    }

    interface RoundPeg {
        double radius();
    }

    static class DefaultRoundPeg implements RoundPeg {
        private final double radius;
        public DefaultRoundPeg(double radius) {
            this.radius = radius;
        }
        @Override
        public double radius() {
            return radius;
        }
    }

    interface SquarePeg {
        double side();
    }

    static class DefaultSquarePeg implements SquarePeg {
        private final double side;
        public DefaultSquarePeg(double side) {
            this.side = side;
        }
        @Override
        public double side() {
            return side;
        }
    }

    static class RoundHoleAdapter implements RoundPeg, SquarePeg{
        double side;
        public RoundHoleAdapter(double side) {
            this.side=side;
        }

        @Override
        public double radius() {
            return side/Math.sqrt(2);
        }

        @Override
        public double side() {
            return side;
        }
    }

}
