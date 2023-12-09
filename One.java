import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class One {
    private static List<String> strengthCardOrder = List.of("A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3",
            "2").reversed();

    public static int getMax(String card) {
        List<String> letters = Arrays.asList(card.split(""));
        Map<String, Integer> mappedLetters = new HashMap<>();

        for (String letter : letters) {
            if (mappedLetters.containsKey(letter))
                mappedLetters.put(letter, mappedLetters.get(letter) + 1);
            else
                mappedLetters.put(letter, 1);
        }

        int max = mappedLetters.values().iterator().next();

        for (int value : mappedLetters.values()) {
            if (value > max) {
                max = value;
            }
        }

        if (max >= 4) {
            max++;
        } else if (max == 3) {
            if (mappedLetters.values().contains(2)) {
                max = 4;
            } else {
                max = 3;
            }
        } else if (max == 2) {
            if (mappedLetters.values().stream().filter(n -> n == 2).count() == 2) {
                max = 2;
            } else {
                max = 1;
            }
        } else {
            max = 0;
        }

        // System.out.println(card + " " + mappedLetters + " " + max);
        return max;
    }

    public static long getResult(List<String> puzzle) {
        long result = 0;

        List<String> cards = puzzle.stream().map(card -> card.split(" ")[0].trim()).toList();
        List<Integer> bids = puzzle.stream().map(card -> Integer.parseInt(card.split(" ")[1].trim())).toList();

        List<String> orderedCards = cards.stream()
                .sorted(Comparator.comparing(One::getMax).thenComparing(compareStrenghIndex())).toList();

        int rank = 1;
        for (String card : orderedCards) {
            int index = cards.indexOf(card);
            int bid = bids.get(index);
            result += (rank * bid);
            // System.out.println("Card: " + card + ", Bid: " + bid + ", Rank: " + rank + ",
            // Result:" + result);
            rank++;
        }

        return result; // 241669085
    }

    private static Comparator<? super String> compareStrenghIndex() {
        return (card1, card2) -> {

            int index = 0;
            int indexA = strengthCardOrder.indexOf(String.valueOf(card1.charAt(index)));
            int indexB = strengthCardOrder.indexOf(String.valueOf(card2.charAt(index)));

            while (indexA == indexB) {
                index++;
                if (index >= card1.length() || index >= card2.length()) {
                    break; // added check to prevent index out of bounds
                }
                indexA = strengthCardOrder.indexOf(String.valueOf(card1.charAt(index)));
                indexB = strengthCardOrder.indexOf(String.valueOf(card2.charAt(index)));
            }
            // System.out.println(index);
            // System.out.println(indexA + " " + indexB);
            // System.out.println(card1 + " " + card2);
            return indexA > indexB ? 1 : -1;

        };
    }
}
