import java.util.List;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        List<String> puzzle = TextFileReader.readTextFile("./puzzle.txt");
        System.out.println("Advent of Code - Day 6\n");

        System.out.print("Part 1 Result: ");
        System.out.println(One.getResult(puzzle));
        System.out.print("Part 2 Result: ");
        System.out.println(Two.getResult(puzzle));
    }
}