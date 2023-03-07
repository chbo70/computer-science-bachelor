package at.ac.uibk.pm.innerclasses.localclass;

public class OuterClass {
    public static void main(String[] args) {
        final int value1 = 1;
        int value2 = 2;
        int value3 = 3;
        int value4 = 4;
        ++value3;

        class LocalInnerClass {
            int innerValue1 = 10;

            LocalInnerClass() {
                System.out.println(value1);
                System.out.println(value2);
                //System.out.println(value3); // Compile-Error!
                //value4 += 4; // Compile-Error!
                System.out.println(innerValue1);
            }
        }

        new LocalInnerClass();
        System.out.println(value1 + value2 + value3 + value4);
        // System.out.println(innerValue1); // Compile-Error!
    }
}
