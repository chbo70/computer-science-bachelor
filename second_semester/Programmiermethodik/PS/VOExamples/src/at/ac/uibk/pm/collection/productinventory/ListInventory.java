package at.ac.uibk.pm.collection.productinventory;

import java.util.*;

/**
 * Class provides implementation of a product inventory based on a list.
 */
public class ListInventory implements Inventory {
    private final List<Product> inventoryList = new ArrayList<>();

    /**
     * Removes all products that are not in stock from inventory.
     */
    @Override
    public void removeUnavailableProducts() {
        Iterator<Product> productIterator = this.inventoryList.iterator();
        while (productIterator.hasNext()) {
            if (!productIterator.next().isInStock()) {
                productIterator.remove();
            }
        }
    }

    /**
     * Prints the contents of the inventory.
     */
    @Override
    public void printInventory() {
        Iterator<Product> inventoryListIterator = this.inventoryList.iterator();
        while (inventoryListIterator.hasNext()) {
            System.out.println(inventoryListIterator.next());
        }

        /*
         * for (Product currProduct : this.inventoryList) {
         * System.out.println(currProduct); }
         */
    }

    /**
     * Removes a given product from the inventory.
     *
     * @param product product to be removed.
     */
    @Override
    public void removeProduct(Product product) {
        for (int i = inventoryList.size() - 1; i >= 0; --i) {
            if (inventoryList.get(i).equals(product)) {
                inventoryList.remove(i);
            }
        }
/* or
        Iterator<Product> productIterator = this.inventoryList.iterator();
        while (productIterator.hasNext()) {
            if (productIterator.next().equals(product)) {
                productIterator.remove();
            }
        }
*/
    }

    /**
     * Adds product to inventory.
     *
     * @param product - product to be added
     */
    @Override
    public void addProduct(Product product) {
        this.inventoryList.add(product);
    }

    /**
     * Adds collection of products to inventory.
     *
     * @param products - collection of products to be added.
     */
    @Override
    public void addProducts(Collection<Product> products) {
        this.inventoryList.addAll(products);
    }

    /**
     * Returns immutable copy (collection) of inventory.
     *
     * @return (immutable) list of all products contained in inventory.
     */
    @Override
    public List<Product> getProducts() {
        return Collections.unmodifiableList(this.inventoryList);
    }

    /**
     * Returns list of most recently added products.
     *
     * @param quantity - number of products to be returned
     * @return list of most recently added products
     */
    public List<Product> getNewestProducts(int quantity) {
        return Collections.unmodifiableList(
                this.inventoryList.subList(this.inventoryList.size() - quantity,
                        this.inventoryList.size()));
    }

}
