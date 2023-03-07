package at.ac.uibk.pm.g06.csaz9837.s09.e01;

import java.io.*;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

public class Application {
    public static int levenshteinDistance(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] distance = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            distance[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            distance[0][j] = j;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                distance[i][j] = Math.min(Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1), distance[i - 1][j - 1] + cost);
            }
        }
        return distance[len1][len2];
    }

    public static double similarity(String s1, String s2) {
        int distance = levenshteinDistance(s1, s2);
        int max = Math.max(s1.length(), s2.length());
        return 1 - (double) distance / max;
    }

    public static double similarityOfRestaurants(Restaurants r1, Restaurants r2) {
        double similarityName = similarity(r1.getName(), r2.getName());
        double similarityAddress = similarity(r1.getAddress(), r2.getAddress());
        double similarityCity = similarity(r1.getCity(), r2.getCity());
        double similarityPhone = similarity(r1.getPhone(), r2.getPhone());
        double similarityType = similarity(r1.getType(), r2.getType());
        return (similarityName + similarityAddress + similarityCity + similarityPhone + similarityType) / 5;
    }

    public static void main(String[] args) {
        try {
            Map<Integer, Restaurants> listOfRestaurants2 = new HashMap<>();
            //List<Restaurants> listOfRestaurants = new java.util.ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("at/ac/uibk/pm/g06/csaz9837/s09/e01/restaurant.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                //skip first line
                if (parts[0].equals("id")) {
                    continue;
                }
                listOfRestaurants2.put(Integer.parseInt(parts[0]), new Restaurants(parts[1], parts[2], parts[3], parts[4], parts[5]));
                //listOfRestaurants.add(new Restaurants(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5]));
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter("at/ac/uibk/pm/g06/csaz9837/s09/e01/result.csv"));
            /*for (int i = 0; i < listOfRestaurants.size(); i++) {
                for (int j = i + 1; j < listOfRestaurants.size(); j++) {
                    double similarity = similarityOfRestaurants(listOfRestaurants.get(i), listOfRestaurants.get(j));
                    if (similarity > 0.75) {
                        writer.write(listOfRestaurants.get(i).getID() + " , " + listOfRestaurants.get(j).getID() + " , " + similarity + "\n");
                    }
                }
            }
            */
            for (int i = 0; i < listOfRestaurants2.keySet().size(); i++) {
                for (int j = i + 1; j < listOfRestaurants2.keySet().size(); j++) {
                    if (listOfRestaurants2.get(i) == null) {
                        break;
                    }
                    double similarity = similarityOfRestaurants(listOfRestaurants2.get(i), listOfRestaurants2.get(j));
                    if (similarity > 0.75 && similarity < 1) {
                        writer.write(i + " , " + j + " , " + similarity + "\n");
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
