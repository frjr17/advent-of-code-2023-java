import java.util.Arrays;
import java.util.List;

public class One {

    public static int getResult(List<String> puzzle) {
        int result = 0;

        for (int lineIndex = 0; lineIndex < puzzle.size(); lineIndex++) {
            int pow = 0;

            List<String> winningNumbers = Arrays
                    .asList(puzzle.get(lineIndex).split(":")[1].split("\\|")[0].trim().split(" ")).stream()
                    .filter(num -> num != "").toList();

            List<String> listNumbers = Arrays
                    .asList(puzzle.get(lineIndex).split(":")[1].split("\\|")[1].trim().split(" ")).stream()
                    .filter(num -> num != "").toList();

            for (String number : listNumbers) {
                if (winningNumbers.contains(number)) {
                    pow++;
                }
            }

            int points = 0;

            while (pow != 0) {
                if (points == 0) {
                    points++;
                } else {
                    points *= 2;
                }
                pow--;
            }
            result += points;
        }

        return result;
    }

}