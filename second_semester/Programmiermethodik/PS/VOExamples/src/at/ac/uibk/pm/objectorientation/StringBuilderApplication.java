package at.ac.uibk.pm.objectorientation;

/**
 * Class shows how strings can be created by using simple string concatenation
 * and a string builder.
 */
public class StringBuilderApplication {

    /**
     * Concatenates n string representations of integers.
     *
     * @param n number of integers to concatenate.
     */
    public static void testString(int n) {
        String s = "";
        for (int i = 0; i < n; ++i) {
            s += (i % 10);
        }
        System.out.println(s);
    }

    /**
     * Concatenates n string representations of integers by using a
     * StringBuilder instance.
     *
     * @param n number of integers to concatenate.
     */
    public static void testStringBuilder(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            sb.append(i % 10);
        }
        String s = sb.toString();
        System.out.println(s);
    }

}
