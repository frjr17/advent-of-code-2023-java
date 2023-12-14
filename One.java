import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class One {
    static List<List<String>> puzzle;
    static List<List<Integer>> galaxies = new ArrayList<>();
    static int addingNum = 2;

    public static long getResult(List<String> mainPuzzle) {
        long result = 0;

        List<List<String>> puzzle = mainPuzzle.stream().map(line -> Arrays.asList(line.split(""))).toList();
        findGalaxies(puzzle);
        List<Integer> rowsWithoutGalaxies = getRowsWithoutGalaxies(puzzle);
        List<Integer> columnsWithoutGalaxies = getColumnsWithoutGalaxies(puzzle);

        // System.out.println();
        // for (Object line : puzzle) {
        // System.out.println(line);
        // }

        for (int i = 0; i < galaxies.size(); i++) {
            List<Integer> g1 = galaxies.get(i);

            for (int j = i + 1; j < galaxies.size(); j++) {
                int counterX = 0;
                int counterY = 0;
                List<Integer> g2 = galaxies.get(j);
                int minY = Math.min(g1.get(0), g2.get(0));
                int maxY = Math.max(g1.get(0), g2.get(0));
                int minX = Math.min(g1.get(1), g2.get(1));
                int maxX = Math.max(g1.get(1), g2.get(1));
                for (int rowIdx : rowsWithoutGalaxies) {
                    if (minY < rowIdx && rowIdx < maxY) {
                        counterY++;
                    }
                }
                for (int colIdx : columnsWithoutGalaxies) {
                    if (minX < colIdx && colIdx < maxX) {
                        counterX++;
                    }
                }
                result += (Math.abs(minY - maxY) + ((addingNum - 1) * counterY) + Math.abs(minX - maxX)
                        + (((addingNum - 1) * counterX)));
            }
        }

        return result;
    }

    private static void findGalaxies(List<List<String>> puzzle) {
        for (int i = 0; i < puzzle.size(); i++) {
            List<String> line = puzzle.get(i);
            for (int j = 0; j < line.size(); j++) {
                String point = line.get(j);
                if (point.equals("#")) {
                    galaxies.add(List.of(i, j));
                }
            }
        }
    }

    private static List<Integer> getColumnsWithoutGalaxies(List<List<String>> puzzle) {
        List<Integer> columnsWithoutGalaxies = new ArrayList<>();
        for (int i = 0; i < puzzle.size(); i++) {
            boolean haveGalaxy = false;
            for (int j = 0; j < puzzle.size(); j++) {
                if (puzzle.get(j).get(i).equals("#")) {
                    haveGalaxy = true;
                    break;
                }
            }
            if (!haveGalaxy) {
                columnsWithoutGalaxies.add(i);
            }
        }
        return columnsWithoutGalaxies;
    }

    private static List<Integer> getRowsWithoutGalaxies(List<List<String>> puzzle) {
        List<Integer> rowsWithoutGalaxies = new ArrayList<>();
        for (int i = 0; i < puzzle.size(); i++) {
            if (!puzzle.get(i).contains("#")) {
                rowsWithoutGalaxies.add(i);
            }
        }
        return rowsWithoutGalaxies;
    }

}
