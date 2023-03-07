package at.ac.uibk.pm.innerclasses.staticinnerclasses;

/**
 * Class simply shows how static inner classes are created.
 */
public class OuterClass {
    private static int outerCount = 1;

    public static class StaticInnerClass {
        private static int innerCount = 3;

        /**
         * Output method for inner count variable.
         */
        public static void print() {
            System.out.println("counter sum: " + (innerCount + outerCount));
            ++outerCount;
            ++innerCount;
        }
    }
}

