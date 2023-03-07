package at.ac.uibk.pm.csaz9837.exam1.e03;

public class EncryptionModel2 {
    public String encipher(String stringToEncipher) {
        StringBuilder encipheredString = new StringBuilder();

        for (int i = 0; i < stringToEncipher.length(); i++) {
            char c = stringToEncipher.toLowerCase().charAt(i);
            char[] reversedAlphabet = new char[26];
            for (int j = 0; j < 26; j++) {
                reversedAlphabet[j] = (char) ('z' - j);
            }
            if (Character.isLetter(c)) {
                c = (char) (reversedAlphabet[c - 'a']);
            }
            encipheredString.append(c);
        }
        return encipheredString.toString();
    }

    public String decipher(String stringTodecipher) {
        StringBuilder decipheredString = new StringBuilder();

        char[] reversedAlphabet = new char[26];
        for (int j = 0; j < 26; j++) {
            reversedAlphabet[j] = (char) ('z' - j);
        }
        for (int i = 0; i < stringTodecipher.length(); i++) {
            char c = stringTodecipher.toLowerCase().charAt(i);
            if (Character.isLetter(c)) {
                c = (char) (reversedAlphabet[c - 'a']);
            }
            decipheredString.append(c);
        }
        return decipheredString.toString();
    }
}
