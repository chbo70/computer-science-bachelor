package at.ac.uibk.pm.streams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.stream.Collectors.joining;

/**
 * Class shows reading files how to read from a file and write to a file.
 */
public class BookReaderApplication {
    private static final int NUMBER_OF_LINES = 5;

    /**
     * Main method for BookReader application.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        Path inputFile = Path.of("src", "resources", "alice_in_wonderland.txt");
        Path outputFile = Path.of("src", "resources", "alice_in_wonderland_out.txt");

        String content = "";
        try (BufferedReader bufferedReader = Files.newBufferedReader(inputFile)) {
            content = bufferedReader.lines()
                    .limit(NUMBER_OF_LINES)
                    .collect(joining("\n"));
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("problems reading from file: " + outputFile);
            e.printStackTrace();
        }

        System.out.println();

        try (BufferedWriter writer = Files.newBufferedWriter(outputFile)) {
            writer.write(content);
            System.out.println("Wrote to file " + outputFile);
        } catch (IOException e) {
            System.out.println("problems writing to file: " + outputFile);
            e.printStackTrace();
        }

    }

}
