import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * One
 */
public class One {

    public static int getResult(Collection<String> puzzle) {
        Collection<Integer> nums = new ArrayList<Integer>();
        int result = 0;

        for (String line : puzzle) {
            String[] numArray = new String[line.length()];

            for (int i = 0; i < numArray.length; i++) {
                String letter = line.split("")[i];

                try {
                    Integer.parseInt(letter);
                    numArray[i] = letter;
                } catch (NumberFormatException e) {
                }
            }

            List<Integer> filteredNumbers = Arrays.asList(numArray).stream().filter((n) -> n != null)
                    .map(n -> Integer.parseInt(n)).collect(Collectors.toList());

            String strNum = filteredNumbers.getFirst().toString() + filteredNumbers.getLast().toString();
            nums.add(Integer.parseInt(strNum));
        }

        for (Integer n : nums) {
            result += n;
        }

        // Result, 57346
        return result;
    }
}