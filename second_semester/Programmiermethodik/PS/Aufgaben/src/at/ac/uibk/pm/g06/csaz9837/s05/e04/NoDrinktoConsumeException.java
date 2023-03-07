package at.ac.uibk.pm.g06.csaz9837.s05.e04;

public class NoDrinktoConsumeException extends Exception {
    private Drink drink;

    public NoDrinktoConsumeException(Drink drink) {
        this.drink = drink;
    }

    @Override
    public String getMessage() {
        return "I don't have a " + this.drink;
    }
}
