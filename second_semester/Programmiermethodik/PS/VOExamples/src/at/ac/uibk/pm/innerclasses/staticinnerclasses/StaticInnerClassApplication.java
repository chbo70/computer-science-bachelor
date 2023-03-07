package at.ac.uibk.pm.innerclasses.staticinnerclasses;


/**
 * Class demonstrates use of static inner classes.
 */
public class StaticInnerClassApplication {
    /**
     * Main method demonstrating inner classes.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        OuterClass.StaticInnerClass.print();
        // StaticInnerClass.print(); // Compile-Error!
    }

}
