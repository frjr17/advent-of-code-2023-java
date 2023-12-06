import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Two {

    public static long getResult(List<String> puzzle) {
        long result = 1;

        long time = Long
                .parseLong(Arrays.asList(puzzle.get(0).split(":")[1].trim().split(" ")).stream().filter(n -> n != "")
                        .collect(Collectors.joining("")));
        long distance = Long
                .parseLong(Arrays.asList(puzzle.get(1).split(":")[1].trim().split(" ")).stream().filter(n -> n != "")
                        .collect(Collectors.joining("")));

        long winning = 0;
        for (long timeIndex = 1; timeIndex < time; timeIndex++) {
            // System.out.println(timeIndex + " " + (timeIndex * (time - timeIndex)));
            if ((timeIndex * (time - timeIndex)) > distance) {
                winning++;
            }
        }

        result = winning;

        return result;
    }
}
