import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class One {

    public static int getResult(List<String> puzzle) {
        int result = 0;

        List<String> directions = Arrays.asList((puzzle.get(0).split("")));
        Map<String, List<String>> mappedDirections = new HashMap<>();

        for (String direction : puzzle.subList(2, puzzle.size())) {
            String key = direction.split(" = ")[0];
            List<String> value = Arrays.asList(direction.split(" = ")[1].substring(1, 9).split(", "));
            mappedDirections.put(key, value);
        }

        String current = "AAA";

        int directionIndex = 0;
        while (!current.equals("ZZZ")) {
            String direction = directions.get(directionIndex % directions.size());
            current = mappedDirections.get(current).get(direction.equals("L") ? 0 : 1);
            directionIndex++;
            result++;
        }

        return result;
    }

}
