import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Two {

    private static long lcm(long x, long y) {
        long max = Math.max(x, y);
        long min = Math.min(x, y);
        long lcm = max;
        while (lcm % min != 0) {
            lcm += max;
        }
        return lcm;
    }

    public static long getResult(List<String> puzzle) {
        long result = 0;

        List<String> directions = Arrays.asList((puzzle.get(0).split("")));
        Map<String, List<String>> mappedDirections = new HashMap<>();

        for (String direction : puzzle.subList(2, puzzle.size())) {
            String key = direction.split(" = ")[0];
            List<String> value = Arrays.asList(direction.split(" = ")[1].substring(1, 9).split(", "));
            mappedDirections.put(key, value);
        }

        List<String> currents = mappedDirections.keySet().stream().filter(s -> s.endsWith("A")).toList();
        List<Long> currentsFinalCount = currents.stream().map(node -> {
            long nodeResult = 0;
            String nodeCurrent = node;
            List<String> currentDirections = new ArrayList<>(directions);
            while (!nodeCurrent.endsWith("Z")) {
                if (currentDirections.isEmpty()) {
                    currentDirections = new ArrayList<>(directions);
                }
                String currentDirection = currentDirections.removeFirst();
                nodeCurrent = mappedDirections.get(nodeCurrent).get(currentDirection.equals("L") ? 0 : 1);
                nodeResult++;
            }

            return nodeResult;
        }).toList();

        result = currentsFinalCount.get(0);
        for (int i = 1; i < currentsFinalCount.size(); i++) {
            result = lcm(result, currentsFinalCount.get(i));
        }

        return result;
    }

}
