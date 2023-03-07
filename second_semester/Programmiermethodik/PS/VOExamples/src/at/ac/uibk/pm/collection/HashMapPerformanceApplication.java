package at.ac.uibk.pm.collection;

import at.ac.uibk.pm.collection.productinventory.Product;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * class is aimed at showcasing behavior of collections with different equals
 * and hashcode implementations
 */
public class HashMapPerformanceApplication {

    public static void main(String[] args) {

        performanceTest();

    }

    /**
     * inserts Product objects into map, measures runtime
     * uses Product as key to demonstrate behavior (correctness and performance) impact
     * of different hashcode() methods of Product-class in this scenario.
     */
    private static void performanceTest() {
        final int numberElements = 20000;
        final int numberRuns = 5;
        Product[] productsArray = new Product[numberElements];
        System.out.println("Inserting elements:");
        Map<Product, Integer> products = new HashMap<>(numberElements * 4 / 3 + 1);
        for (int i = 0; i < productsArray.length; ++i) {
            productsArray[i] = new Product(i, 42.00, "Some Product", "Manufacturer",
                    "Description", 3);
            products.put(productsArray[i], i);
            if (i % 1000 == 0) {
                System.out.printf("%5d, hashcode: %5d%n", i, productsArray[i].hashCode());
            }
        }
        System.out.println("-".repeat(80));
        System.out.println("Searching elements:");
        double[] executionTimes = new double[numberRuns];
        for (int i = 0; i < executionTimes.length; i++) {
            long startTime = System.nanoTime();
            for (Product product : productsArray) {
                products.get(product);
            }
            long endTime = System.nanoTime();
            double executionTime = (endTime - startTime) / 1e6;
            System.out.printf("Operation took: %.1f ms%n", executionTime);
            System.out.println("Search result: " + products.get(productsArray[0]));
            executionTimes[i] = executionTime;
        }
        System.out.println("-".repeat(80));
        Arrays.sort(executionTimes);
        System.out.printf("Median execution time: %.1f ms%n",
                executionTimes[executionTimes.length / 2]);
    }

}
