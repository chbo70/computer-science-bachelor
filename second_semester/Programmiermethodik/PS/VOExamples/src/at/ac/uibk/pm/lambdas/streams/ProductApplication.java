package at.ac.uibk.pm.lambdas.streams;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductApplication {
    public static void main(String[] args) {
        Map<Product, Integer> shoppingList = new LinkedHashMap<>();
        shoppingList.put(new Product(5, new BigDecimal("1"), "Chocolate"), 2);
        shoppingList.put(new Product(1, new BigDecimal("1.2"), "Milk"), 2);
        shoppingList.put(new Product(4, new BigDecimal("2.5"), "Potato"), 1);
        shoppingList.put(new Product(3, new BigDecimal("1.1"), "Mango"), 3);

        BigDecimal totalPrice =
                shoppingList
                        .entrySet()
                        .stream()
                        .map(e -> e.getKey().getPrice().multiply(BigDecimal.valueOf(e.getValue())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("totalPrice: %.2f%n%n", totalPrice);

        String pricePerArticle =
                shoppingList
                        .entrySet()
                        .stream()
                        .map(e -> String.format("%-10s %.2f", e.getKey().getName(),
                                e.getKey().getPrice().multiply(BigDecimal.valueOf(e.getValue()))))
                        .collect(Collectors.joining("\n"));
        System.out.printf("Total price per product:%n%s%n%n", pricePerArticle);

        List<Integer> unavailableProducts = Arrays.asList(3, 8, 9);
        List<Integer> shoppingBasket =
                shoppingList
                        .keySet()
                        .stream()
                        .map(Product::getProductID)
                        .filter(productID -> !unavailableProducts.contains(productID))
                        .sorted()
                        .toList();
        System.out.printf("Product ids in shopping basket:%n%s%n", shoppingBasket);
    }
}
