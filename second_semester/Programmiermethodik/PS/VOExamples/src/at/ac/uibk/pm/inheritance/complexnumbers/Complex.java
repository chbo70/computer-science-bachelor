package at.ac.uibk.pm.inheritance.complexnumbers;

/**
 * Interface for complex numbers.
 */
public interface Complex {
    /**
     * Method returns the real part of the complex number.
     *
     * @return real part
     */
    double getReal();

    /**
     * Method returns the imaginary part of the complex number.
     *
     * @return imaginary part
     */
    double getImaginary();

    /**
     * Method returns distance between complex number and 0.
     *
     * @return distance of number to 0.
     */
    double getDistance();

    /**
     * Returns the phase of the complex number.
     *
     * @return phase of complex number.
     */
    double getPhase();

    /**
     * Multiplies given complex number with second complex number.
     *
     * @param other complex number to be used for multiplication.
     * @return product of the two given complex numbers.
     */
    Complex multiply(Complex other);

    /**
     * Adds given complex number to second complex number.
     *
     * @param other complex number to be added.
     * @return sum of the two given complex numbers.
     */
    Complex add(Complex other);
}
