package at.ac.uibk.pm.g06.csaz9837.s09.e01;

import org.junit.jupiter.api.Test;

import static at.ac.uibk.pm.g06.csaz9837.s09.e01.Application.levenshteinDistance;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {
    @Test
    void testLevenshteinDistance() {
        String s1 = "abc";
        String s2 = "abd";
        String s3 = "abc";
        String s4 = "";
        assertEquals(1, levenshteinDistance(s1, s2));
        assertEquals(0, levenshteinDistance(s1, s3));
        assertEquals(3, levenshteinDistance(s1, s4));
    }

    @Test
    void testSimilarity() {
        //test similarity
        String s1 = "abcd";
        String s2 = "abcc";
        String s3 = "abc";
        String s4 = "";
        assertEquals(0.75, Application.similarity(s1, s2));
        assertEquals(0.75, Application.similarity(s1, s3));
        assertEquals(0, Application.similarity(s1, s4));
    }

    @Test
    void testSimilarityOfRestaurants() {
        Restaurants restaurants1 = new Restaurants(43, "valentino", "3115 pico blvd.", "santa monica", "310/829-4313", "italian");
        Restaurants restaurants2 = new Restaurants(44, "valentino", "3115 pico blvd.", "santa monica", "310/829-4313", "italian");

        Restaurants restaurants3 = new Restaurants(45, "philippe's the original", "1001 n. alameda st.", "chinatown", "213/628-3781", "american");
        Restaurants restaurants4 = new Restaurants(46, "philippe's the original", "1001 n. alameda st.", "chinatown", "213/628-3781", "alirican");

        assertEquals(1, Application.similarityOfRestaurants(restaurants1, restaurants2));
        assertEquals(0.95, Application.similarityOfRestaurants(restaurants3, restaurants4));
    }
}