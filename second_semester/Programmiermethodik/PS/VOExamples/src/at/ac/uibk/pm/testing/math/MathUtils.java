package at.ac.uibk.pm.testing.math;

public class MathUtils {

    private MathUtils() {

    }

    /**
     * Returns whether the number n is prime.
     *
     * @param primeCandidate prime candidate
     * @return the result
     */
    public static boolean isPrime(final int primeCandidate) {
        if (primeCandidate <= 1) {
            return false;
        }
        for (int divisor = 2; divisor * divisor <= primeCandidate; ++divisor) {
            if (primeCandidate % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the n-th number of the Fibonacci sequence
     *
     * @param n n-th number of the Fibonacci sequence is computed
     * @return the result
     * @throws ArithmeticException      if the result overflows a long
     * @throws IllegalArgumentException if the argument is negative
     */
    public static long fibonacciNumber(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException(
                    String.format("Expected non-negative integer but got %d", n));
        }
        long previous = 0;
        long current = 1;
        if (n <= 1) {
            return n;
        }
        for (int i = 2; i <= n; ++i) {
            final long currentTmp = current;
            current = Math.addExact(current, previous);
            previous = currentTmp;
        }
        return current;
    }
}
