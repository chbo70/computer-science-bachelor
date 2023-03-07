package at.ac.uibk.pm.g06.csaz9837.s09.e02;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<String> listAy = new ArrayList<>();
        List<String> listQu = new ArrayList<>();
        int ayCounter = 0;
        int quCounter = 0;

        try {
            BufferedReader ayReader = new BufferedReader(new FileReader("at/ac/uibk/pm/g06/csaz9837/s09/e02/ay.txt"));
            BufferedReader quReader = new BufferedReader(new FileReader("at/ac/uibk/pm/g06/csaz9837/s09/e02/qu.txt"));
            String currentLine;
            while ((currentLine = ayReader.readLine()) != null) {
                listAy.add(currentLine);
            }
            while ((currentLine = quReader.readLine()) != null) {
                listQu.add(currentLine);
            }

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Please enter a text: ");
            String[] inputWords = inputReader.readLine().split(" ");

            for (String word : inputWords) {
                if (listAy.contains(word)) {
                    ayCounter++;
                }
                if (listQu.contains(word)) {
                    quCounter++;
                }
            }

            System.out.println("QU: " + (double) quCounter / inputWords.length);
            System.out.println("AY: " + (double) ayCounter / inputWords.length);

            inputReader.close();
            ayReader.close();
            quReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
