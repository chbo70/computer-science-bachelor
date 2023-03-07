package at.ac.uibk.pm.inheritance.complexnumbers;

/**
 * Class provides means to create complex number based on polar representation
 * (including also addition, multiplication and other computations).
 */
public class Polar implements Complex {

    private final double distance;
    private final double phase;

    /**
     * Constructs a complex number based on its polar representation.
     *
     * @param distance the distance of the complex number from 0.
     * @param phase    the phase of the complex number.
     */
    public Polar(double distance, double phase) {
        this.distance = distance;
        this.phase = phase;
    }

    /**
     * Method returns the real part of the complex number.
     *
     * @return real part
     */
    @Override
    public double getReal() {
        return distance * Math.cos(phase);
    }

    /**
     * Method returns the imaginary part of the complex number.
     *
     * @return imaginary part
     */
    @Override
    public double getImaginary() {
        return distance * Math.sin(phase);
    }

    /**
     * Method returns distance between complex number and 0.
     *
     * @return distance of number to 0.
     */
    @Override
    public double getDistance() {
        return distance;
    }

    /**
     * Returns the phase of the complex number.
     *
     * @return phase of complex number.
     */
    @Override
    public double getPhase() {
        return phase;
    }

    /**
     * Multiplies given complex number with second complex number.
     *
     * @param other complex number to be used for multiplication.
     * @return product of the two given complex numbers.
     */
    @Override
    public Complex multiply(Complex other) {
        return new Polar(distance * other.getDistance(),
                phase + other.getPhase());
    }

    /**
     * Adds given complex number to second complex number.
     *
     * @param other complex number to be added.
     * @return sum of the two given complex numbers.
     */
    @Override
    public Complex add(Complex other) {
        return new Cartesian(getReal() + other.getReal(),
                getImaginary() + other.getImaginary());
    }

}