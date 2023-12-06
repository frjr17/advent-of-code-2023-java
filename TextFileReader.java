import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TextFileReader {
    public static List<String> readTextFile(String filePath) {
        Path path = Paths.get(filePath);
        List<String> puzzle = new ArrayList<String>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line as needed
                puzzle.add(line.strip().trim());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return puzzle;
    }
}
