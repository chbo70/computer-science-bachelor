package at.ac.uibk.pm.comparable.product;

import java.util.Objects;

/**
 * Class provides means for storing information about products.
 */
public class Product implements Comparable<Product> {
    private final int productID;
    private final String name;

    /**
     * Constructs product object
     *
     * @param productID unique identifier of the product.
     * @param name      name of the product.
     */
    public Product(int productID, String name) {
        this.productID = productID;
        this.name = name;
    }

    public int getProductID() {
        return productID;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Product{productID=%d, name='%s'}", productID, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return productID == product.productID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID);
    }

    @Override
    public int compareTo(Product o) {
        return Integer.compare(productID, o.productID);
    }
}
