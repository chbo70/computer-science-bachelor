package at.ac.uibk.pm.comparable.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ProductApplication {
    public static void main(String[] args) {
        Optional<Product> product = findProduct(2);
        product.ifPresentOrElse(
                p -> System.out.println(p.getName()),
                () -> System.out.println("Product not found"));
    }

    public static Optional<Product> findProduct(final int productID) {
        List<Product> products = new ArrayList<>(List.of(new Product(1, "Milk")));
        for (Product product : products) {
            if (product.getProductID() == productID) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }
}
