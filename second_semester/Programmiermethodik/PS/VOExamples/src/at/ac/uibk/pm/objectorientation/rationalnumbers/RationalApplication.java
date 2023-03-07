package at.ac.uibk.pm.objectorientation.rationalnumbers;

/**
 * Class provides means to test rational implementations.
 */
public class RationalApplication {

    /**
     * Main method for rational application
     *
     * @param args not used
     */
    public static void main(String[] args) {
        Rational three = new Rational(3);
        three.printRational();

        Rational twoThirds = new Rational(2, 3);
        twoThirds.printRational();

        Rational fourThirds = twoThirds.add(twoThirds);
        fourThirds.printRational();
    }

}
