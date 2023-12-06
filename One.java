import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class One {

    public static long getResult(List<String> puzzle) {
        long result = 0;

        List<Long> seeds = Arrays.asList(puzzle.get(0).split(":")[1].trim().split(" ")).stream().filter(n -> n != " ")
                .map(Long::parseLong).toList();

        List<String> converterStrings = puzzle.stream().filter(s -> s.contains("map:")).toList();

        List<Long> current = seeds;

        for (int converterStringIndex = 0; converterStringIndex < converterStrings.size() - 1; converterStringIndex++) {
            List<List<String>> conversions = puzzle
                    .subList(puzzle.indexOf(converterStrings.get(converterStringIndex)) + 1,
                            puzzle.indexOf(converterStrings.get(converterStringIndex + 1)) - 1)
                    .stream().map(n -> Arrays.asList(n.trim().split(" "))).toList();

            List<Long> tempCurrent = new ArrayList<>();
            for (Long seed : current) {
                tempCurrent.add(seed);
            }
            // System.out.println("\n" + converterStrings.get(converterStringIndex));
            // System.out.println("Before: " + current);
            // System.out.println();
            for (List<String> conversion : conversions) {
                // System.out.println("Conversion: " + conversion);
                Long destination = Long.parseLong(conversion.get(0));
                Long source = Long.parseLong(conversion.get(1));
                Long range = Long.parseLong(conversion.get(2));

                for (int currentIndex = 0; currentIndex < current.size(); currentIndex++) {
                    Long seed = current.get(currentIndex);
                    if (seed >= source && seed < source + range) {
                        tempCurrent.set(currentIndex, (destination - source) + seed);
                    }
                }
                // System.out.println(tempCurrent);
            }
            // System.out.println();
            current = tempCurrent;
            // System.out.println("After: " + current);
        }

        List<List<String>> conversions = puzzle
                .subList(puzzle.indexOf(converterStrings.getLast()) + 1,
                        puzzle.size() - 1)
                .stream().map(n -> Arrays.asList(n.trim().split(" "))).toList();

        List<Long> tempCurrent = new ArrayList<>();
        for (Long seed : current) {
            tempCurrent.add(seed);
        }
        // System.out.println("\n" + converterStrings.getLast());
        // System.out.println("Before: " + current);
        // System.out.println();
        for (List<String> conversion : conversions) {
            // System.out.println("Conversion: " + conversion);
            Long destination = Long.parseLong(conversion.get(0));
            Long source = Long.parseLong(conversion.get(1));
            Long range = Long.parseLong(conversion.get(2));

            for (int currentIndex = 0; currentIndex < current.size(); currentIndex++) {
                Long seed = current.get(currentIndex);
                if (seed >= source && seed < source + range) {
                    tempCurrent.set(currentIndex, (destination - source) + seed);
                }
            }
            // System.out.println(tempCurrent);
        }
        // System.out.println();
        current = tempCurrent;
        // System.out.println("After: " + current);

        result = current.stream().min(Long::compareTo).orElse(0L);

        return result;
    }
}
