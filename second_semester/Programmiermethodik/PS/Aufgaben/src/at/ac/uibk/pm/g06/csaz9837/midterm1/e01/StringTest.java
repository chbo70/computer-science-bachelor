package at.ac.uibk.pm.g06.csaz9837.midterm1.e01;

public class StringTest {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "World";
        s2 += "!";
        String s4 = s2 + "!";
        String s3 = s1 + " " + s2;
        System.out.println(s3);
    }
}
