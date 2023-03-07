package Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnagramTest {
    @ParameterizedTest()
    @CsvSource({"hello, hello", "'', ''", "eleven plus two, twelve plus one"})
    void isAnagram(String s1, String s2){
        assertTrue(Anagram.isAnagram(s1,s2));
    }

    @ParameterizedTest()
    @CsvSource({"HELLO, hello", "abc, de", "hello, he", "aabc, abc"})
    void isNoAnagram(String s1, String s2){
        assertFalse(Anagram.isAnagram(s1,s2));
    }

    @ParameterizedTest()
    @CsvSource({"hello, hello", "'', ''", "eleven plus two, twelve plus one"})
    void isAnagram2(String s1, String s2){
        assertTrue(Anagram.isAnagram2(s1,s2));
    }

    @ParameterizedTest()
    @CsvSource({"HELLO, hello", "abc, de", "hello, he", "aabc, abc"})
    void isNoAnagram2(String s1, String s2){
        assertFalse(Anagram.isAnagram2(s1,s2));
    }

}