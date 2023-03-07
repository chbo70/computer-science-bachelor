package at.ac.uibk.pm.g06.csaz9837.s08.e03;

import java.util.function.BooleanSupplier;

public class EagerChecker {
    static String eagerChecker(boolean first, boolean second) {
        return first && second ? "match" : "incompatible!";
    }
}
