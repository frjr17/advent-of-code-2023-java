import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;

public class Two {
    public static int getResult(Collection<String> puzzle) {
        // Setting letter nums
        Map<String, Integer> letterNums = new HashMap<String, Integer>();
        letterNums.put("one", 1);
        letterNums.put("two", 2);
        letterNums.put("three", 3);
        letterNums.put("four", 4);
        letterNums.put("five", 5);
        letterNums.put("six", 6);
        letterNums.put("seven", 7);
        letterNums.put("eight", 8);
        letterNums.put("nine", 9);

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

            letterNums.keySet().forEach(key -> {
                int index = line.indexOf(key);
                while (index != -1) {
                    numArray[index] = letterNums.get(key).toString();
                    index = line.indexOf(key, index + 1);
                }
            });

            List<Integer> filteredNumbers = Arrays.asList(numArray).stream().filter((n) -> n != null)
                    .map(n -> Integer.parseInt(n)).collect(Collectors.toList());

            String strNum = filteredNumbers.getFirst().toString() + filteredNumbers.getLast().toString();
            nums.add(Integer.parseInt(strNum));
        }

        for (Integer n : nums) {
            result += n;
        }

        return result;
    }
}
