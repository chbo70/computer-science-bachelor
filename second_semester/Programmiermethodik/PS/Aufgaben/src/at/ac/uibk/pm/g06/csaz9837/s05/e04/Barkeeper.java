package at.ac.uibk.pm.g06.csaz9837.s05.e04;

public class Barkeeper extends Person {
    private Bar theBarIWorkIn;

    public Barkeeper(String name) {
        super(name);
    }

    public void serveDrink(Guest guest, Drink drinkToServe, int amountOfDrink) {
        guest.setDrink(drinkToServe);
        guest.setCurrentAmountOfDrink(amountOfDrink);
        System.out.println("The drink is served!");
    }

    @Override
    public String toString() {
        return "The barkeeper " + super.getName() + " is called!";
    }
}
