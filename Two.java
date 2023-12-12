import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Two {
    static List<List<String>> puzzle;
    static List<String> visitedCoords = new ArrayList<>();

    public static long getResult(List<String> mainPuzzle) {
        long result = 0;

        puzzle = mainPuzzle.stream().map(node -> Arrays.asList(node.split(""))).toList();
        List<Integer> startPositionCoord = getStartPositionCoord(puzzle);
        int startY = startPositionCoord.get(0);
        int startX = startPositionCoord.get(1);
        List<Integer> solutions = new ArrayList<>();
        solutions.add(getCounter(startY, startX + 1, "right"));
        solutions.add(getCounter(startY - 1, startX, "up"));
        solutions.add(getCounter(startY + 1, startX, "down"));
        solutions.add(getCounter(startY - 1, startX, "left"));

        for (int i = 0; i < puzzle.size(); i++) {
            int wallCount = 0;
            String tempPoint = "";
            for (int j = 0; j < puzzle.get(0).size(); j++) {
                String point = puzzle.get(i).get(j);
                if (visitedCoords.contains(Arrays.asList(i, j).toString())) {
                    if (point.equals("|")) {
                        wallCount += 1;
                    } else if ("FL".contains(point)) {
                        tempPoint = point;
                    } else if (point.equals("J")) {
                        if (tempPoint.equals("F")) {
                            wallCount += 1;
                        }
                        tempPoint = "";
                    } else if (point.equals("7")) {
                        if (tempPoint.equals("L")) {
                            wallCount += 1;
                        }
                        tempPoint = "";
                    }
                } else {
                    if (wallCount % 2 == 1) {
                        result += 1;
                    }
                }
            }
        }

        return result;
    }

    private static int getCounter(int startY, int startX, String direction) {
        List<Object> resultPath = new ArrayList<>();
        List<Integer> currentPosition = Arrays.asList(startY, startX);
        int counter = 0;

        do {
            int currentPositionY = currentPosition.get(0);
            int currentPositionX = currentPosition.get(1);
            resultPath = getNextDirection(Arrays.asList(currentPositionY, currentPositionX), direction);
            direction = (String) resultPath.get(1);
            currentPosition = (List<Integer>) resultPath.get(0);
            counter++;
        } while (!direction.isEmpty() || !currentPosition.isEmpty());

        return counter;
    }

    private static List<Object> getNextDirection(List<Integer> position, String direction) {
        List<Integer> newPosition = new ArrayList<>();
        String newDirection = "";
        int y = position.get(0);
        int x = position.get(1);
        String coord;
        try {
            coord = puzzle.get(y).get(x);

        } catch (Exception e) {
            return Arrays.asList(newPosition, newDirection);
        }

        visitedCoords.add(position.toString());
        if (!coord.equals(".")) {
            if (coord.equals("|")) {
                if (direction.equals("up") && y != 0) {
                    newPosition.add(y - 1);
                    newPosition.add(x);
                    newDirection = "up";
                } else if (direction.equals("down") && y != puzzle.size()) {
                    newPosition.add(y + 1);
                    newPosition.add(x);
                    newDirection = "down";
                }

            }
            if (coord.equals("-")) {
                if (direction.equals("left") && x != 0) {
                    newPosition.add(y);
                    newPosition.add(x - 1);
                    newDirection = "left";
                } else if (direction.equals("right") && x != puzzle.get(0).size()) {
                    newPosition.add(y);
                    newPosition.add(x + 1);
                    newDirection = "right";
                }

            }
            if (coord.equals("L")) {
                if (direction.equals("left") && y != 0) {
                    newPosition.add(y - 1);
                    newPosition.add(x);
                    newDirection = "up";
                } else if (direction.equals("down") && x != puzzle.get(0).size() - 1) {
                    newPosition.add(y);
                    newPosition.add(x + 1);
                    newDirection = "right";
                }

            }
            if (coord.equals("J")) {
                if (direction.equals("right") && y != 0) {
                    newPosition.add(y - 1);
                    newPosition.add(x);
                    newDirection = "up";
                } else if (direction.equals("down") && x != 0) {
                    newPosition.add(y);
                    newPosition.add(x - 1);
                    newDirection = "left";
                }

            }
            if (coord.equals("7")) {
                if (direction.equals("right") && y != puzzle.size() - 1) {
                    newPosition.add(y + 1);
                    newPosition.add(x);
                    newDirection = "down";
                } else if (direction.equals("up") && x != 0) {
                    newPosition.add(y);
                    newPosition.add(x - 1);
                    newDirection = "left";
                }

            }
            if (coord.equals("F")) {
                if (direction.equals("left") && y != puzzle.size() - 1) {
                    newPosition.add(y + 1);
                    newPosition.add(x);
                    newDirection = "down";
                } else if (direction.equals("up") && x != puzzle.get(0).size() - 1) {
                    newPosition.add(y);
                    newPosition.add(x + 1);
                    newDirection = "right";
                }

            }
        }

        return Arrays.asList(newPosition, newDirection);
    }

    private static List<Integer> getStartPositionCoord(List<List<String>> puzzle) {
        List<Integer> startPositionCoord = new ArrayList<>();
        for (int i = 0; i < puzzle.size(); i++) {
            for (int j = 0; j < puzzle.get(0).size(); j++) {
                if (puzzle.get(i).get(j).equals("S")) {
                    startPositionCoord.add(i);
                    startPositionCoord.add(j);
                }
            }
        }
        return startPositionCoord;
    }
}
