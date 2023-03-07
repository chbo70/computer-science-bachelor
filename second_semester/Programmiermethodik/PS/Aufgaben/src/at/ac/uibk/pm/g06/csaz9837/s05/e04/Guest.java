package at.ac.uibk.pm.g06.csaz9837.s05.e04;

public class Guest extends Person {
    private Bar currentlyVisitedBar;
    private Drink drink;
    private int currentAmountOfDrink;

    public Guest(String name) {
        super(name);
    }

    public void getCurrentlyVisitedBar() {
        System.out.println(this.currentlyVisitedBar);
    }

    public void setCurrentlyVisitedBar(Bar currentlyVisitedBar) {
        this.currentlyVisitedBar = currentlyVisitedBar;
    }

    public void getCurrentDrink() {
        System.out.println(drink + " is my current Drink");
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public int getCurrentAmountOfDrink() {
        System.out.println("I got " + currentAmountOfDrink + " Drinks");
        return currentAmountOfDrink;
    }

    public void consumeDrink(Drink drink) throws NoDrinktoConsumeException {
        if (drink == getDrink()) {
            setCurrentAmountOfDrink(currentAmountOfDrink - 1);
            System.out.println("I just drank my " + drink);
        } else {
            throw new NoDrinktoConsumeException(drink);
        }
    }

    public void setCurrentAmountOfDrink(int currentAmountOfDrink) {
        this.currentAmountOfDrink = currentAmountOfDrink;
    }

    public void checkInstanceOfOtherGuest(Guest otherGuest) {
        if (super.getName().equals(otherGuest.getName())) {
            System.out.println("There is another " + super.getName() + " in this Bar");
        } else {
            System.out.println("I am the only " + super.getName() + " in this Bar");
        }
    }

    @Override
    public String toString() {
        return "I, " + super.getName() + ", am a guest!";
    }
}
