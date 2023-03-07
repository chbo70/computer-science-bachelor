package at.ac.uibk.pm.testing.math;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class MathUtilsTest {
    @ParameterizedTest(name = "isPrime({0}) => true")
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 15601})
    void isPrime(int value) {
        assertTrue(MathUtils.isPrime(value));
    }

    @ParameterizedTest(name = "isPrime({0}) => false")
    @ValueSource(ints = {-2, -1, 0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 25, 910})
    void isNotPrime(int value) {
        assertFalse(MathUtils.isPrime(value));
    }

    @Test
    @DisplayName("fibonacciNumber(-1) => IllegalArgumentException")
    void invalidFibonacciNumberInput() {
        assertThrows(IllegalArgumentException.class, () -> MathUtils.fibonacciNumber(-1));
    }

    @ParameterizedTest(name = "fibonacciNumber({0}) => {1}")
    @CsvSource({"0, 0", "1, 1", "2, 1", "3, 2", "4, 3", "5, 5", "6, 8", "7, 13"})
    void fibonacciNumberInput(final int input, final int expectedOutput) {
        assertEquals(expectedOutput, MathUtils.fibonacciNumber(input));
    }

    @Test
    @DisplayName("fibonacciNumber(46) => 1836311903")
    void largeFibonacciNumber() {
        assertEquals(1836311903, MathUtils.fibonacciNumber(46));
    }

    @Test
    @DisplayName("fibonacciNumber(93) => ArithmeticException")
    void overflowFibonacciNumber() {
        assertThrows(ArithmeticException.class, () -> MathUtils.fibonacciNumber(93));
    }
}
