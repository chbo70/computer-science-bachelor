package at.ac.uibk.pm.collection.productinventory;

import java.util.Collection;

/**
 * Interface holds specification of a inventory for managing products, their
 * metadata and stock.
 */
public interface Inventory {
    /**
     * Prints contents of inventory.
     */
    void printInventory();

    /**
     * Removes a given product from the inventory.
     *
     * @param product product to be removed.
     */
    void removeProduct(Product product);

    /**
     * Removes all products not in stock anymore from inventory.
     */
    void removeUnavailableProducts();

    /**
     * Adds a given product to the inventory.
     *
     * @param product - product to be added.
     */
    void addProduct(Product product);

    /**
     * Adds one or more products to the inventory.
     *
     * @param products - collection of products to be added.
     */
    void addProducts(Collection<Product> products);

    /**
     * Returns a collection containing all products.
     *
     * @return (immutable) list of all products contained in inventory.
     */
    Collection<Product> getProducts();
}
