import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Two {
    public static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { -1, 1 }, { -1, -1 },
            { 1, -1 }, { 1, 1 } };

    public static int getResult(List<String> puzzle) {
        int result = 0;

        int puzzleLength = puzzle.size();
        int lineLength = puzzle.get(0).length();

        Map<String, List<Integer>> gearRatioCandidates = new HashMap<>();

        for (int lineIndex = 0; lineIndex < puzzleLength; lineIndex++) {
            List<Character> digits = new ArrayList<>();
            List<String> adjacent = new ArrayList<>();

            for (int charIndex = 0; charIndex < lineLength; charIndex++) {
                char c = puzzle.get(lineIndex).charAt(charIndex);

                if (Character.isDigit(c)) {
                    digits.add(c);

                    if (!adjacent.isEmpty()) {
                        continue;
                    }

                    for (int[] direction : DIRECTIONS) {
                        int x = charIndex + direction[0];
                        int y = lineIndex + direction[1];

                        if (0 <= x && x < lineLength && 0 <= y && y < puzzleLength) {
                            char adjacentChar = puzzle.get(y).charAt(x);

                            if (adjacent.isEmpty() && adjacentChar == '*') {
                                adjacent.add(x + "," + y);
                                break;
                            }
                        }
                    }
                } else {
                    if (!adjacent.isEmpty()) {
                        int partNumber = numberFromDigits(digits);
                        for (String xy : adjacent) {
                            gearRatioCandidates
                                    .computeIfAbsent(xy, k -> new ArrayList<>())
                                    .add(partNumber);
                        }
                    }
                    digits.clear();
                    adjacent.clear();
                }
            }

            if (!adjacent.isEmpty()) {
                int partNumber = numberFromDigits(digits);
                for (String xy : adjacent) {
                    gearRatioCandidates
                            .computeIfAbsent(xy, k -> new ArrayList<>())
                            .add(partNumber);
                }
            }
        }

        result = gearRatioCandidates.values()
                .stream()
                .filter(gears -> gears.size() == 2)
                .mapToInt(gears -> gears.get(0) * gears.get(1))
                .sum();

        return result;
    }

    public static int numberFromDigits(List<Character> digits) {
        StringBuilder builder = new StringBuilder(digits.size());
        for (Character digit : digits) {
            builder.append(digit);
        }
        return Integer.parseInt(builder.toString());
    }
}
