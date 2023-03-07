package at.ac.uibk.pm.csaz9837.exam1.e03;

public class CipherApplication {

    public static void cipherMethod(EncryptionModel1 model1, String stringToCipher) {
        String enciphered = model1.encipher(stringToCipher);
        System.out.println(enciphered);
        String deciphered = model1.decipher(enciphered);
        System.out.println(deciphered);
    }

    public static void cipherMethod(EncryptionModel2 model2, String stringToCipher) {
        String enciphered = model2.encipher(stringToCipher);
        System.out.println(enciphered);
        String deciphered = model2.decipher(enciphered);
        System.out.println(deciphered);
    }

    public static void main(String[] args) {
        EncryptionModel1 method1 = new EncryptionModel1();
        EncryptionModel2 method2 = new EncryptionModel2();

        String stringToCipher = "Z-Ach!se0";
        System.out.println("original string: " + stringToCipher);
        System.out.println("method 1:");
        cipherMethod(method1, stringToCipher);
        System.out.println("method 2:");
        cipherMethod(method2, stringToCipher);
    }
}