package at.ac.uibk.pm.g06.csaz9837.s07.e01;

public class Item extends Entity<Long>{
    private final String name;
    private final double price;

    public Item(Long id, String name, double price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "name of item: " + name + ", price: " + price;
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }
}
