package JavaFiles;

import java.util.Stack;

public class PalindromTest {

    //method to find all palindroms in a string efficiently
    public static void findAllPalindroms(String text) {
        int textLength = text.length();
        int[] lps = new int[textLength];
        int i = 1;
        int j = 0;
        while (i < textLength) {
            if (text.charAt(i) == text.charAt(j)) {
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
        while (i < textLength) {
            if (text.charAt(i) == text.charAt(j)) {
                System.out.println("Palindrom found at index " + i);
                i++;
                j++;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        String inputtedString = "Geist ziert Leben, Mut hegt Siege, Beileid tragt belegbare Reue, Neid dient nie, nun eint Neid die neuerer, abgelebt gart die Liebe, geist geht, umnebelt reizt Sieg.";
        String inputtedString_c = inputtedString.toUpperCase();
        //inputtedString_c = inputtedString_c.replaceAll(",","");
        inputtedString_c = inputtedString_c.replaceAll("\\W","");
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < inputtedString_c.length(); i++) {
            stack.push(inputtedString_c.charAt(i));
        }

        String reversedString = "";

        while (!stack.isEmpty()){
            reversedString = reversedString+stack.pop();
        }
        if (inputtedString_c.equals(reversedString)){
            System.out.println("The inputted String is a palindrome");
        }
        else {
            System.out.println("The inputted String is not a palindrome");
        }

        findAllPalindroms(inputtedString_c);

    }
}
