package at.ac.uibk.pm.g06.csaz9837.s04.e04;

public class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
        if (name == null || price <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }
}
