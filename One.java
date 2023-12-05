import java.util.ArrayList;
import java.util.List;

public class One {
    public static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { -1, 1 }, { -1, -1 },
            { 1, -1 }, { 1, 1 } };

    public static int getResult(List<String> puzzle) {
        int result = 0;
        int puzzleLength = puzzle.size();
        int lineLength = puzzle.get(0).length();

        for (int lineIndex = 0; lineIndex < puzzleLength; lineIndex++) {
            List<Character> digits = new ArrayList<>();
            boolean adjacent = false;

            for (int charIndex = 0; charIndex < lineLength; charIndex++) {
                char c = puzzle.get(lineIndex).charAt(charIndex);

                if (Character.isDigit(c)) {
                    digits.add(c);

                    if (adjacent) {
                        continue;
                    }

                    for (int[] direction : DIRECTIONS) {
                        int x = charIndex + direction[0];
                        int y = lineIndex + direction[1];

                        if (0 <= x && x < lineLength && 0 <= y && y < puzzleLength) {
                            char adjacentChar = puzzle.get(y).charAt(x);

                            if (!adjacent && adjacentChar != '.' && !Character.isDigit(adjacentChar)) {
                                adjacent = true;
                                break;
                            }
                        }
                    }
                } else {
                    if (adjacent) {
                        result += numberFromDigits(digits);
                    }
                    digits.clear();
                    adjacent = false;
                }
            }

            if (adjacent) {
                result += numberFromDigits(digits);
            }
        }

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