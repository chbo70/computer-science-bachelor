package at.ac.uibk.pm.g06.csaz9837.s06.e01;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        FibonacciIterator fibIt = new FibonacciIterator(5);
        System.out.println(fibIt.hasNext());
        while (fibIt.hasNext()) {
            BigInteger value = fibIt.next();
            System.out.println(value + " ");
        }
    }
}
