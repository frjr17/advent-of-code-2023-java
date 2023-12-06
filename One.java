import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class One {

    public static int getResult(List<String> puzzle) {
        int result = 1;

        List<Integer> times = Arrays.asList(puzzle.get(0).split(":")[1].trim().split(" ")).stream().filter(n -> n != "")
                .map(Integer::parseInt).toList();
        List<Integer> distances = Arrays.asList(puzzle.get(1).split(":")[1].trim().split(" ")).stream()
                .filter(n -> n != "")
                .map(Integer::parseInt).toList();

        for (int timesIndex = 0; timesIndex < times.size(); timesIndex++) {
            int distance = distances.get(timesIndex);
            int time = times.get(timesIndex);
            int winning = 0;
            for (int timeIndex = 1; timeIndex < time; timeIndex++) {
                // System.out.println(timeIndex + " " + (timeIndex * (time - timeIndex)));
                if ((timeIndex * (time - timeIndex)) > distance) {
                    winning++;
                }
            }

            result *= winning;
        }

        return result;
    }
}
