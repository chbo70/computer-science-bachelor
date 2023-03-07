package at.ac.uibk.pm.generics.pair;

/**
 * Class shows a first example for dealing with generics, provides means to
 * store a pair of values (potentially of different types).
 *
 * @param <T> datatype of first element of pair.
 * @param <U> datatype of second element of pair.
 */
public class Pair<T, U> {
    private final T first;
    private final U second;

    /**
     * Constructs pair based on given two values.
     *
     * @param first  first value of pair.
     * @param second second value of pair.
     */
    public Pair(final T first, final U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns first element of pair.
     *
     * @return first element of pair (of type T).
     */
    public T getFirst() {
        return this.first;
    }

    /**
     * Returns second element of pair.
     *
     * @return second element of pair (of type U).
     */
    public U getSecond() {
        return this.second;
    }
}