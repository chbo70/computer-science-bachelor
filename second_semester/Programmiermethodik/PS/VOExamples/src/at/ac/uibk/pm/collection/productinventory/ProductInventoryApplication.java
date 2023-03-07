package at.ac.uibk.pm.collection.productinventory;

import java.util.List;

/**
 * Class provides means for storing information about products using diverse
 * collection implementations.
 */

public class ProductInventoryApplication {
    /**
     * Main method for showing different inventory implementations.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        // list version of inventory
        Inventory listInventory = new ListInventory();

        Product katana = new Product(2, 126.90, "Katana", "La Sportiva",
                "added features for even higher climbing performance.", 0);
        Product solution = new Product(3, 73.91, "Solution Alex Honnold Edition", "Black Diamond",
                " lightweight with an ergonomic fit that is particularly comfortable", 0);
        Product proDry = new Product(4, 148.99, "Skimmer Pro Dry 7.1mm", "edelrid", "minimal weight and small diameter",
                42);

        // add products to list of products available
        listInventory.addProduct(new Product(1, 59.99, "Grigri", "Petzl", "Best belaying device ever.", 99));
        listInventory.addProducts(List.of(katana, solution, proDry));


        listInventory.printInventory();
        listInventory.removeUnavailableProducts();
        listInventory.printInventory();

        // map version of inventory
        Inventory mapInventory = new MapInventory();

        // add products to map based on product id
        mapInventory.addProduct(new Product(1, 59.99, "Grigri", "Petzl", "Best belaying device ever.", 99));
        mapInventory.addProducts(List.of(katana, solution, proDry));

        mapInventory.printInventory();
        mapInventory.removeUnavailableProducts();
        mapInventory.printInventory();

    }

}
