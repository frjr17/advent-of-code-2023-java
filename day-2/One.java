import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * One
 */
public class One {

    public static List<String> convertCubes(String cube) {

        return Arrays.asList(cube.split(" ")).stream().filter(c -> c != "").toList();

    }

    public static int getResult(Collection<String> puzzle) {
        int result = 0;
        for (String line : puzzle) {
            boolean isValid = true;
            String id = line.split(":")[0];
            id = id.split(" ")[1];
            String[] subsets = line.split(":")[1].split(";");
            Map<String, Integer> lineColors = new HashMap<String, Integer>();

            lineColors.put("red", 0);
            lineColors.put("green", 0);
            lineColors.put("blue", 0);

            for (String subset : subsets) {
                Collection<String> list = Arrays.asList(subset.split(","));
                List<List<String>> cubes = list.stream().map(c -> One.convertCubes(c)).toList();

                for (List<String> cube : cubes) {
                    int quantity = Integer.parseInt(cube.getFirst());
                    String color = cube.getLast();

                    if (color.equals("green") && quantity > 13) {
                        isValid = false;
                        break;
                    }
                    if (color.equals("red") && quantity > 12) {
                        isValid = false;
                        break;
                    }
                    if (color.equals("blue") && quantity > 14) {
                        isValid = false;
                        break;
                    }

                    lineColors.put(color, lineColors.get(color) + quantity);
                }
            }

            if (isValid) {
                result += Integer.parseInt(id);
            }
        }

        return result;
    }
}