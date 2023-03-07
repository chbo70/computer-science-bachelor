package at.ac.uibk.pm.generics.pair;

/**
 * Class demonstrates use of generic Pairs.
 */
public final class PairApplication {
    /**
     * Main method for generic pairs.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        final Pair<Integer, Integer> integerPair = new Pair<Integer, Integer>(42, 11);
        System.out.println(integerPair.getFirst());
        System.out.println(integerPair.getSecond());
        // integerPair = new Pair<String, String>("TEST","1"); // Error!
        Pair<Integer, Pair<Integer, Integer>> integerPairPair = new Pair<Integer, Pair<Integer, Integer>>(2,
                integerPair);
        Pair<Integer, Pair<Integer, Integer>> integerPairPair2 = new Pair<>(2, integerPair);

        System.out.println(integerPairPair.getSecond().getSecond());
    }
}
