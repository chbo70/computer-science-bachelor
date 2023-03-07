package Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Anagram {
    private static Map<Character, Integer> letterFrequency(String s){
        Map<Character, Integer> frequency = new HashMap<Character, Integer>();
        for (char c : s.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }
        return frequency;
    }
    public static boolean isAnagram(String s1, String s2){
        char[] c1 = s1.toCharArray(); //len(s1) = n
        char[] c2 = s2.toCharArray(); //len(s2) = m BigO(n*log n + n*log n)
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1,c2);
    }
    public static boolean isAnagram2(String s1, String s2){
        return letterFrequency(s1).equals(letterFrequency(s2));
    }
    public static boolean isAnagramCaseInsensitive(String s1, String s2){
        return isAnagram2(s1.toLowerCase(), s2.toLowerCase());
    }
}
