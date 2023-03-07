package at.ac.uibk.pm.g06.csaz9837.s08.e03;

import java.util.function.BooleanSupplier;

public class LazyChecker {
    static String lazyChecker(BooleanSupplier first, BooleanSupplier second) {
        return first.getAsBoolean() && second.getAsBoolean() ? "match" : "incompatible!";
    }
}
