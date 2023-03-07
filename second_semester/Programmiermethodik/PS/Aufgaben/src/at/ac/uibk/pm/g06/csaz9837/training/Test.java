package at.ac.uibk.pm.g06.csaz9837.training;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        List<BigInteger> values = List.of(BigInteger.TEN,BigInteger.valueOf(5), BigInteger.valueOf(30));

        //Optional<BigInteger> maxDiff2 = Optional.of(list.stream().max(BigInteger::compareTo).get().subtract(list.stream().min(BigInteger::compareTo).get()));
        //System.out.println(maxDiff2);

        Optional<BigInteger> maxDiff = values.stream().collect(BigIntegerCollector.maxDifference());
        System.out.println(maxDiff);

    }
}
