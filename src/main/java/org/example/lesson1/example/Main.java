package org.example.lesson1.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Есть двумерная карта с роботами
        // Роботы это объекты, они могут перемещаться по карте
        // Карта имеет какие-то границы
        // 2 робота не могут находиться в одной точке одновременно
        // Роботы имеют направление (которое можно менять (поворачивать)),
        //              роботы идут только вперед (или стоят на месте).

        Scanner userInput = new Scanner(System.in);

        System.out.println("Добро пожаловать в игру!");
        System.out.println("...описание...");

        RobotMap map;
        while (true) {
            System.out.println("Для создания карты введите 2 положительных числа через пробел");
            try {
                int n = userInput.nextInt();
                int m = userInput.nextInt();
                userInput.nextLine();

                map = new RobotMap(n, m);
                break;
            } catch (RobotMapCreationException | InputMismatchException e) {
                System.err.println("Возникла ошибка при создании карты: " + e.getMessage());
            } catch (Throwable e) {
                System.err.println("Возникла ошибка на стороне сервера: " + e.getMessage());
                System.exit(1);
            }
        }

        System.out.println("Карта успешно создана!");

        CommandManager commandManager = new CommandManager(map);

        System.out.println("Для просмотра списка допустимых команд введите h");

        while (true) {
            System.out.println("Введите команду");
            String command = userInput.nextLine();
            try {
                String commandExecutionResult = commandManager.handleCommand(command);
                if (Objects.nonNull(commandExecutionResult) && !commandExecutionResult.isBlank()) {
                    System.out.println(commandExecutionResult);
                }
            } catch (CommandNotFoundException e) {
                System.err.println("Команда [" + e.getMessage() + "] не найдена");
            } catch (CommandExecutionException e) {
                System.err.println("Во время исполнения команды произошла ошибка: " + e.getMessage());
            } catch (RobotMoveException e) {
                e.printStackTrace();
            }
        }

    }

    private static class CommandManager {

        private final RobotMap map;
        private final Map<String, CommandExecutor> commands;

        public CommandManager(RobotMap map) {
            this.map = map;

            commands = new HashMap<>();
            commands.put("h", this::printHelp);
            commands.put("q", this::quit);
            commands.put("a", this::addRobot);
            commands.put("l", this::listRobots);
            commands.put("cd", this::changeDirection);
            commands.put("m", this::move);

            // FIXME: 27.01.2023
        }

        public String handleCommand(String command) throws CommandNotFoundException, CommandExecutionException, RobotMoveException {
            String[] split = command.split(" ");
            String commandCode = split[0];

            CommandExecutor executor = commands.get(commandCode);
            if (executor == null) {
                throw new CommandNotFoundException(command);
            }

            String[] args = Arrays.copyOfRange(split, 1, split.length);
            return executor.execute(args);
        }

        private String addRobot(String[] args) throws CommandExecutionException {
            if (args.length < 2) {
                throw new CommandExecutionException("Недостаточно аргументов");
            }

            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);

            try {
                map.createRobot(new Point(x, y));
            } catch (RobotCreationException e) {
                throw new CommandExecutionException(e.getMessage());
            }

            return null;
        }

        private String listRobots(String[] args) {
            List<String> robotsInfo = new ArrayList<>();
            for (RobotMap.Robot r : map.getRobots()) {
                robotsInfo.add("id: " + r.getId() + ", point: " + r.getPoint());
            }
            return robotsInfo.toString();
        }

        private String changeDirection(String[] args) throws CommandExecutionException {
            if (args.length < 2) {
                throw new CommandExecutionException("Недостаточно аргументов");
            }
            for (RobotMap.Robot r : map.getRobots()) {
                if (r.getId() == Long.parseLong(args[0])) {
                    switch (args[1]) {
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
            }
            return "";
        }

        private String move(String[] args) throws CommandExecutionException, RobotMoveException {
            if (args.length!=2 & args.length!=1) {
                throw new CommandExecutionException("Неверное количество аргументов");
            }
            if (args.length == 1) {
                for (RobotMap.Robot r : map.getRobots()) {
                    if (r.getId() == Long.parseLong(args[0])) {
                        r.move();
                        return "Переместились на один шаг " + r.getDirection();
                    }
                }

            } else {
                for (RobotMap.Robot r : map.getRobots()) {
                    if (r.getId() == Long.parseLong(args[0])) {
                        r.move(Integer.parseInt(args[1]));
                        return "Переместились на " + Integer.parseInt(args[1]) + " шагов " + r.getDirection();
                    }
                }
            }
            return "";
        }

        private String printHelp(String[] args) {
            return """
                    h                  -> распечатать список допустимых команд (help)
                    a 1 2              -> создать робота на позиции (1, 2) (add)
                    l                  -> распечатать всех роботов (list)
                    m id [5]           -> перемещаем робота на 1 единицу вперед (move)
                    cd id [t, r, b, l] -> изменить направление робота (change direction)
                    q                  -> завершить программу (quit)
                    """;
        }

        private String quit(String[] args) {
            System.exit(0);
            return null;
        }

        private interface CommandExecutor {
            String execute(String[] args) throws CommandExecutionException, RobotMoveException;
        }

    }

    private void homework() {
        // Доделать остальные команды move, change direction и list
    }

}
