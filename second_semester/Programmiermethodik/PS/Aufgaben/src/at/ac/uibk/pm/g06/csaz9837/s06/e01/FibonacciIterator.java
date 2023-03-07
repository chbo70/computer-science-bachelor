package at.ac.uibk.pm.g06.csaz9837.s06.e01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class FibonacciIterator implements Iterator<BigInteger>, Iterable<BigInteger> {
    FibonacciIterator fibonacciIterator;
    private long n;
    private BigInteger currentNumber = BigInteger.ZERO;
    private BigInteger nextNumber = BigInteger.ONE;
    private long count = 0;

    public FibonacciIterator(long n) {
        this.n = n;
    }

    @Override
    public boolean hasNext() {
        return count < n;
    }

    @Override
    public BigInteger next() {
        if (n == 0) {
            throw new NoSuchElementException();
        }
        if (count == 0) {
            count++;
            return currentNumber;
        } else if (count == 1) {
            count++;
            return nextNumber;
        } else {
            BigInteger currentSolution = currentNumber.add(nextNumber);
            currentNumber = nextNumber;
            nextNumber = currentSolution;
            count++;
            return currentSolution;
        }
    }

    public Iterator<BigInteger> iterator() {
        return fibonacciIterator;
    }
}
