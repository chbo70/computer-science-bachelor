package at.ac.uibk.pm.objectorientation.stack;

/**
 * Class provides main method for testing Stack implementation.
 */
public class StackApplication {

    /**
     * Main method for stack application.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        LinkedListStackWithSizeCounter evenNumberStrings = new LinkedListStackWithSizeCounter();
        LinkedListStackWithSizeCounter oddNumberStrings = new LinkedListStackWithSizeCounter();
        evenNumberStrings.push("hello");
        oddNumberStrings.push("students");

        for (int i = 0; i < 100; ++i) {
            String number = String.valueOf(i);
            if (i % 2 == 0) {
                evenNumberStrings.push(number);
            } else {
                oddNumberStrings.push(number);
            }
        }

        System.out.println("The sizes are " + evenNumberStrings.size() + " and " +
                oddNumberStrings.size());
        while (!evenNumberStrings.isEmpty()) {
            System.out.println(evenNumberStrings.pop());
        }
        System.out.println("The sizes are " + evenNumberStrings.size() + " and " +
                oddNumberStrings.size());
    }

}
