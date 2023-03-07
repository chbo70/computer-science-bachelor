package at.ac.uibk.pm.exceptions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Class demonstrates use of finally for closing files when working with
 * streams. It basically reads a file and reconstructs its content using a
 * StringBuilder.
 */
public class FinallyApplication {

    /**
     * Method reads a file and reconstructs its content by facilitating a
     * StringBuilder instance.
     *
     * @param fileName name and path of file to be read
     * @return content of file (string) or null, if any problem occurred.
     */

    public static String readFile(String fileName) {
        String s = null;
        Reader r = null;
        try {
            r = new FileReader(fileName);
            StringBuilder sb = new StringBuilder();
            int c = r.read();
            while (c != -1) {
                sb.append((char) c);
                c = r.read();
            }
            s = sb.toString();
        } catch (FileNotFoundException e) {
            System.err.println("Could not open file " + fileName);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Problem occurred while reading file");
            e.printStackTrace();
        } finally {
            System.out.println("Here in finally-block.");
            if (r != null) {
                System.out.println("Closing file");
                try {
                    r.close();
                } catch (IOException e) {
                    System.err.println("Problem occurred while closing file");
                    e.printStackTrace();
                }
            }
        }
        return s;
    }

    /**
     * The main method for testing finally.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        System.out.println(readFile("InputFile.txt"));
    }
}
