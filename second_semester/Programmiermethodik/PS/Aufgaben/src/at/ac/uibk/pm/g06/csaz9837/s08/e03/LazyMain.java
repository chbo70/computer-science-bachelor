package at.ac.uibk.pm.g06.csaz9837.s08.e03;

public class LazyMain {
    static boolean higherThan100(int n) {
        System.out.println("Some expensive computations...");
        return n > 100;
    }
    public static void main(String [] args) {
        System.out.print(EagerChecker.eagerChecker(higherThan100(1), higherThan100(200)));
        System.out.println("\nNow with lazy evaluation...");
        System.out.print(LazyChecker.lazyChecker(() -> higherThan100(1), () -> higherThan100(200)));
    }

}
