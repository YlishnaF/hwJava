package org.example.lesson7;

public class Car {

    public static Long counter = 1L;

    public Long id;
    public Engine engine;

    public Car() {
        id = counter;
        engine = new Engine();
        counter++;
    }

    public class Engine {

        public void start() {
            System.out.println("START");
        }

        public void stop() {
            System.out.println("STOP");
        }

    }

    public Engine getEngine() {
        return engine;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                '}';
    }
}
