package at.ac.uibk.pm.csaz9837.exam1.e03;

public class EncryptionModel1 {
    private final int KEY = 4;

    public String encipher(String stringToEncipher) {
        StringBuilder encipheredString = new StringBuilder();

        for (int i = 0; i < stringToEncipher.length(); i++) {
            char c = stringToEncipher.toLowerCase().charAt(i);
            if (Character.isLetter(c)) {
                c = (char) (c + KEY);
                if (c > 'z') {
                    c = (char) (c - 26);
                }
            }
            encipheredString.append(c);
        }
        return encipheredString.toString();
    }

    public String decipher(String stringToDecipher) {
        StringBuilder decipheredString = new StringBuilder();

        for (int i = 0; i < stringToDecipher.length(); i++) {
            char c = stringToDecipher.toLowerCase().charAt(i);
            if (Character.isLetter(c)) {
                c = (char) (c - KEY);
                if (c < 'a') {
                    c = (char) (c + 26);
                }
            }
            decipheredString.append(c);
        }
        return decipheredString.toString();
    }
}
