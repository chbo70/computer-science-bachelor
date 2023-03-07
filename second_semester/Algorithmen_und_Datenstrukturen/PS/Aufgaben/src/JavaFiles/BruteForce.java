package JavaFiles;

import java.util.Arrays;

public class BruteForce {
    //method to find pattern in a string using bruteforce algorithm
    public static void findPattern(String pattern, String text) {
        int patternLength = pattern.length();
        int textLength = text.length();
        for (int i = 0; i < textLength - patternLength + 1; i++) {
            int j = 0;
            while (j < patternLength && pattern.charAt(j) == text.charAt(i + j)) {
                j++;
            }
            if (j == patternLength) {
                System.out.println("Pattern found at index " + i);
            }
        }
    }
    //method to find pattern in text using knuth-morris-pratt algorithm (kmp)
    //what is the failure function?
    public static void findPattern2(String pattern, String text) {
        int patternLength = pattern.length();
        int textLength = text.length();
        int[] lps = new int[patternLength];
        int j = 0;
        int i = 1;
        while (i < patternLength) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                lps[i] = j + 1;
                i++;
                j++;
            } else if (j == 0) {
                lps[i] = 0;
                i++;
            } else {
                j = lps[j - 1];
            }
        }
        i = 0;
        j = 0;
        System.out.println(Arrays.toString(lps));
        while (i < textLength) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
            if (j == patternLength) {
                System.out.println("Pattern found at index " + (i - j));
                j = 0;
            }
        }
    }
    //method computeFailKMP(String pattern) to compute the failure function of the pattern
    public static int[] computeFailKMP(String pattern) {
        int[] fail = new int[pattern.length()];
        int k = 0;
        int j = 1;
        while (j < pattern.length()) {
            if (pattern.charAt(j) == pattern.charAt(k)) {
                fail[j] = k + 1;
                j++;
                k++;
            } else if (k > 0) {
                k = fail[k - 1];
            } else {
                j = j+1;
            }
        }
        return fail;
    }

    public static void main(String[] args) {
        String text = "aaabaadaabaaa";
        String pattern = "aabaaa";
        findPattern(pattern, text);
        findPattern2(pattern, text);
        int[] fail = computeFailKMP(pattern);
        System.out.println(Arrays.toString(fail));

    }
}
