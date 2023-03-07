package at.ac.uibk.pm.objectorientation.rationalnumbers;

/**
 * Class stores rational numbers and provides methods for multiplying, adding,
 * negating rational numbers based on normalized rational numbers.
 */

public class Rational {

    private long numerator;

    private long denominator;

    /**
     * Constructs a normalized rational.
     *
     * @param numerator   nominator
     * @param denominator denominator
     */
    public Rational(long numerator, long denominator) {
        checkDenominator(denominator);
        this.numerator = numerator;
        this.denominator = denominator;
        this.normalize();
    }

    /**
     * Constructs a rational number representing an integer.
     *
     * @param integer number to be represented as rational.
     */
    public Rational(long integer) {
        this(integer, 1);
    }


    /**
     * Method computes the greatest common divisor of two given numbers.
     *
     * @param x First number
     * @param y Second number
     * @return greatest common divisor of x and y
     */
    private static long gcd(long x, long y) {
        return y == 0 ? Math.abs(x) : gcd(y, x % y);
    }

    /**
     * Adds given rational number to second rational number.
     *
     * @param other rational number to add to
     * @return sum of two given rational numbers
     */
    public Rational add(Rational other) {
        return new Rational(this.numerator * other.getDenominator() + this.denominator * other.getNumerator(),
                this.denominator * other.getDenominator());
    }

    /**
     * Returns Denominator.
     *
     * @return denominator
     */
    public long getDenominator() {
        return this.denominator;
    }

    /**
     * Returns Numerator.
     *
     * @return numerator
     */
    public long getNumerator() {
        return this.numerator;
    }

    /**
     * Multiplies given rational number with second rational number.
     *
     * @param other rational number to multiply with
     * @return product of two given rational numbers
     */
    public Rational multiply(Rational other) {
        return new Rational(this.numerator * other.getNumerator(),
                this.denominator * other.getDenominator());
    }

    /**
     * Negates given rational number.
     *
     * @return negated rational number.
     */
    public Rational negate() {
        return new Rational(-this.numerator, this.denominator);
    }

    /**
     * Method normalizes rational number.
     */
    private void normalize() {
        if (this.denominator < 0) {
            this.denominator = -this.denominator;
            this.numerator = -this.numerator;
        }
        long gcd = gcd(this.numerator, this.denominator);
        this.numerator /= gcd;
        this.denominator /= gcd;
    }

    /**
     * Sets denominator.
     *
     * @param denominator Denominator
     */
    public void setDenominator(long denominator) {
        checkDenominator(denominator);
        this.denominator = denominator;
        this.normalize();
    }

    /**
     * Sets numerator.
     *
     * @param numerator Numerator
     */
    public void setNumerator(long numerator) {
        this.numerator = numerator;
        this.normalize();
    }

    /**
     * Prints a string representation of the rational number.
     */
    public void printRational() {
        System.out.println(this.numerator + "/" + this.denominator);
    }

    /**
     * Checks if the denominator is valid (e.g., not zero).
     *
     * @param denominator denominator
     */
    private static void checkDenominator(long denominator) {
        if (denominator == 0) {
            System.err.println("division by zero");
        }
    }

}
