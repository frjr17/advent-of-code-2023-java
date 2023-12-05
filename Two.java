import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Two {

    public static int getResult(List<String> puzzle) {
        int result = 0;
        Map<Integer, Integer> instances = new HashMap<>();

        for (int lineIndex = 0; lineIndex < puzzle.size(); lineIndex++) {
            instances.put(lineIndex + 1, 1);
        }

        for (int lineIndex = 0; lineIndex < puzzle.size(); lineIndex++) {
            int matchingNumbers = 0;

            Integer lineID = Integer.parseInt(Arrays.asList(puzzle.get(lineIndex).split(":")[0].split(" ")).getLast());

            List<String> winningNumbers = Arrays
                    .asList(puzzle.get(lineIndex).split(":")[1].split("\\|")[0].trim().split(" ")).stream()
                    .filter(num -> num != "").toList();

            List<String> listNumbers = Arrays
                    .asList(puzzle.get(lineIndex).split(":")[1].split("\\|")[1].trim().split(" ")).stream()
                    .filter(num -> num != "").toList();

            for (String number : listNumbers) {
                if (winningNumbers.contains(number)) {
                    matchingNumbers++;
                }
            }

            int cardLength = instances.get(lineID);
            if (matchingNumbers != 0) {
                for (int i = 0; i < cardLength; i++) {
                    for (int cardIndex = 1; cardIndex <= matchingNumbers; cardIndex++) {
                        instances.put(lineID + cardIndex, instances.get(lineID + cardIndex) + 1);
                    }
                }
            }
        }

        result = instances.values().stream().reduce(0, Integer::sum);

        return result;
    }

}