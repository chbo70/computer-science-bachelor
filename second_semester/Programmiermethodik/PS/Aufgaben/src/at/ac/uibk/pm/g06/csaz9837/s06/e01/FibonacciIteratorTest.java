package at.ac.uibk.pm.g06.csaz9837.s06.e01;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciIteratorTest {
    @Test
    void hasNextEmptyCollectionTest() {
        FibonacciIterator fibIter = new FibonacciIterator(0);
        assertFalse(fibIter.hasNext());
    }

    @Test
    void nextEmptyCollectionTest() {
        FibonacciIterator fibIter = new FibonacciIterator(0);
        assertThrows(NoSuchElementException.class, () -> fibIter.next());
    }

    @Test
    @DisplayName("test hasNext multiple times")
    void hasNextTest() {
        FibonacciIterator fibIter = new FibonacciIterator(1);
        for (int i = 0; i < 5; i++) {
            assertTrue(fibIter.hasNext());
        }
    }

    @Test
    void fibNumberTests() {
        FibonacciIterator fibIter = new FibonacciIterator(10);
        ArrayList<BigInteger> numbers = new ArrayList<>();
        while (fibIter.hasNext()) {
            numbers.add(fibIter.next());
        }
        //long[] fibNumberTo10 = {0,1,1,2,3,5,8,13,21,34};
        BigInteger[] fibNumberTo10 = {BigInteger.ZERO, BigInteger.ONE, BigInteger.ONE, BigInteger.TWO,
                BigInteger.valueOf(3), BigInteger.valueOf(5), BigInteger.valueOf(8),
                BigInteger.valueOf(13), BigInteger.valueOf(21), BigInteger.valueOf(34)};
        for (int i = 0; i < 10; i++) {
            assertEquals(fibNumberTo10[i], numbers.get(i));
        }
    }
}