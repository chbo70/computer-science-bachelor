package at.ac.uibk.pm.g06.csaz9837.s05.e04;

public class Drink {
    private String name;
    private double price;
    private int upperBound;
    private int lowerBound;

    public Drink(String name, int upperBound, int lowerBound, double price) {
        this.name = name;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.price = price;
    }

    public void checkOrderAmount(int orders) {
        if (orders < upperBound && orders > lowerBound) {
            System.out.println("This Drink is available :)");
        } else {
            throw new AmountToHighException();
        }
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String printPrice() {
        return String.format("Drink \t %.2f Euro", getPrice());
    }

    @Override
    public String toString() {
        return "Drink{}";
    }
}
