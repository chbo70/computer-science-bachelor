package at.ac.uibk.pm.inheritance.complexnumbers;

/**
 * Class provides means to create complex number based on cartesian
 * representation (including also addition, multiplication and other
 * computations).
 */
public class Cartesian implements Complex {

    private final double real;
    private final double imaginary;

    /**
     * Constructs a complex number based on its cartesian representation.
     *
     * @param real      the real part of the complex number.
     * @param imaginary the imaginary part of the complex number.
     */
    public Cartesian(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Method returns the real part of the complex number.
     *
     * @return real part
     */
    @Override
    public double getReal() {
        return real;
    }

    /**
     * Method returns the imaginary part of the complex number.
     *
     * @return imaginary part
     */
    @Override
    public double getImaginary() {
        return imaginary;
    }

    /**
     * Method returns distance between complex number and 0.
     *
     * @return distance of number to 0.
     */
    @Override
    public double getDistance() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    /**
     * Returns the phase of the complex number.
     *
     * @return phase of complex number.
     */
    @Override
    public double getPhase() {
        return Math.atan2(imaginary, real);
    }

    /**
     * Multiplies given complex number with second complex number.
     *
     * @param other complex number to be used for multiplication.
     * @return product of the two given complex numbers.
     */
    @Override
    public Complex multiply(Complex other) {
        return new Cartesian(real * other.getReal() - imaginary * other.getImaginary(),
                real * other.getImaginary() + imaginary * other.getReal());
    }

    /**
     * Adds given complex number to second complex number.
     *
     * @param other complex number to be added.
     * @return sum of the two given complex numbers.
     */
    @Override
    public Complex add(Complex other) {
        return new Cartesian(real + other.getReal(),
                imaginary + other.getImaginary());
    }

}
