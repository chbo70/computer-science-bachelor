package at.ac.uibk.pm.g06.csaz9837.training;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class BigIntegerCollector implements Collector<BigInteger, List<BigInteger>, Optional<BigInteger>> {

    public static BigIntegerCollector maxDifference() {
        return new BigIntegerCollector();
    }

    //erstellt die neue Liste
    @Override
    public Supplier<List<BigInteger>> supplier() {
        return ArrayList::new;
    }

    //fügt die Elemente der Liste hinzu
    @Override
    public BiConsumer<List<BigInteger>, BigInteger> accumulator() {
        return List::add;
    }

    //wird nicht benötigt
    @Override
    public BinaryOperator<List<BigInteger>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    //beinhaltet die letzte Operation
    @Override
    public Function<List<BigInteger>, Optional<BigInteger>> finisher() {
        return list -> {
            if (list.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(list.stream().max(BigInteger::compareTo).get().subtract(list.stream().min(BigInteger::compareTo).get()));
        };
    }

    //setzt die Eigenschaften des Collectors
    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.UNORDERED);
    }
}
