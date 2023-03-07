package at.ac.uibk.pm.lambdas.streams;

import java.math.BigDecimal;

/**
 * Class provides means for storing information about products.
 */
public class Product {
    private final int productID;
    private final BigDecimal price;
    private final String name;

    /**
     * Constructs product object
     *
     * @param productID    unique identifier of the product.
     * @param price        price of the product. TODO what about null?
     * @param name         name of the product. TODO what about null?
     */
    public Product(int productID, BigDecimal price, String name) {
        this.productID = productID;
        this.price = price;
        this.name = name;
    }

    public int getProductID() {
        return productID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

}