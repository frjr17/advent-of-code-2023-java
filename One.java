import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class One {

    public static long getResult(List<String> puzzle) {
        long result = 0;
        List<List<Long>> historySet = getHistorySet(puzzle);

        for (List<Long> history : historySet) {

            List<List<Long>> differences = getDifferences(history);
            List<Long> historyValues = getHistoryValues(differences);

            result += historyValues.stream().reduce(0L, (a, b) -> a + b);

        }

        return result;
    }

    private static List<Long> getHistoryValues(List<List<Long>> differences) {
        List<Long> historyValues = new ArrayList<>();

        long current = differences.get(0).getLast();
        for (int i = 0; i + 1 < differences.size(); i++) {
            long secondLast = differences.get(i + 1).getLast();
            current = current + secondLast;
        }

        historyValues.add(current);
        return historyValues;
    }

    private static List<List<Long>> getDifferences(List<Long> history) {
        List<List<Long>> differences = new ArrayList<>();
        differences.add(history);
        List<Long> difference = new ArrayList<>(history);

        while (!difference.stream().filter(num -> num != 0).toList().isEmpty()) {
            List<Long> newDifference = new ArrayList<>();
            for (int i = 0; i + 1 < difference.size(); i++) {
                newDifference.add(difference.get(i + 1) - difference.get(i));
            }
            difference = newDifference;
            differences.add(difference);
        }
        return differences;
    }

    private static List<List<Long>> getHistorySet(List<String> puzzle) {
        return puzzle.stream()
                .map(list -> Arrays.asList(list.split(" ")).stream().map(Long::parseLong).toList()).toList();
    }

}
