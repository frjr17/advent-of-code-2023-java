import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Two {

    public static List<String> convertCubes(String cube) {
        return Arrays.asList(cube.split(" ")).stream().filter(c -> c != "").toList();
    }

    public static int getResult(Collection<String> puzzle) {
        int result = 0;

        for (String line : puzzle) {
            String id = line.split(":")[0];
            id = id.split(" ")[1];
            String[] subsets = line.split(":")[1].split(";");
            Collection<Integer> redList = new ArrayList<Integer>();
            Collection<Integer> greenList = new ArrayList<Integer>();
            Collection<Integer> blueList = new ArrayList<Integer>();

            for (String subset : subsets) {
                Collection<String> list = Arrays.asList(subset.split(","));
                List<List<String>> cubes = list.stream().map(c -> One.convertCubes(c)).toList();

                for (List<String> cube : cubes) {
                    Integer quantity = Integer.parseInt(cube.getFirst());
                    String color = cube.getLast();

                    if (color.equals("red")) {
                        redList.add(quantity);
                    }

                    if (color.equals("green")) {
                        blueList.add(quantity);
                    }

                    if (color.equals("blue")) {
                        greenList.add(quantity);
                    }

                }
            }

            int redResult = Collections.max(redList);
            int greenResult = Collections.max(greenList);
            int blueResult = Collections.max(blueList);

            result += (redResult * greenResult * blueResult);

        }

        return result;
    }
}
