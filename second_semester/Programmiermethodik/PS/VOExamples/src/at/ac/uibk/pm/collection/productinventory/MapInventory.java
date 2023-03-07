package at.ac.uibk.pm.collection.productinventory;

import java.util.*;

/**
 * Class provides implementation of a product inventory based on a hashmap.
 */
public class MapInventory implements Inventory {
    private final Map<Integer, Product> inventoryMap = new HashMap<>();

    /**
     * Removes all products that are not in stock from inventory.
     */
    @Override
    public void removeUnavailableProducts() {
        Iterator<Product> productIterator = this.inventoryMap.values().iterator();
        while (productIterator.hasNext()) {
            Product currentProduct = productIterator.next();
            if (!currentProduct.isInStock()) {
                productIterator.remove();
            }
        }
    }

    /**
     * Prints the contents of the inventory.
     */
    @Override
    public void printInventory() {

        Iterator<Product> inventoryMapIterator =  inventoryMap.values().iterator();
        while (inventoryMapIterator.hasNext()) {
            System.out.println(inventoryMapIterator.next());
        }


        /*
         * for (Product product : inventoryMap.values()) {
         *      System.out.println(product);
         * }
         */
    }

    /**
     * Removes a given product from the inventory.
     *
     * @param product product to be removed.
     */
    @Override
    public void removeProduct(Product product) {
        inventoryMap.remove(product.getProductID());
    }

    /**
     * Adds product to inventory.
     *
     * @param product - product to be added
     */
    @Override
    public void addProduct(Product product) {
        this.inventoryMap.put(product.getProductID(), product);
    }

    /**
     * Adds collection of products to inventory.
     *
     * @param products - collection of products to be added.
     */
    @Override
    public void addProducts(Collection<Product> products) {
        Iterator<Product> productIterator = products.iterator();
        while (productIterator.hasNext()) {
            Product newProduct = productIterator.next();
            this.inventoryMap.put(newProduct.getProductID(), newProduct);
        }
    }

    /**
     * Returns immutable copy (collection) of inventory.
     *
     * @return collection (immutable) of all products contained in inventory.
     */
    @Override
    public Collection<Product> getProducts() {
        return Collections.unmodifiableCollection(this.inventoryMap.values());
    }

}
