package at.ac.uibk.pm.g06.csaz9837.s05.e04;

public class Beer extends Drink {
    private static final String NAME = "Beer";
    private static final double PRICE = 2.60;
    private static final int UPPER_LIMIT = 6;
    private static final int LOWER_LIMIT = 1;

    public Beer() {
        super(NAME, UPPER_LIMIT, LOWER_LIMIT, PRICE);
    }

    public String getName() {
        return NAME;
    }

    @Override
    public String printPrice() {
        return String.format("Beer \t \t \t %.2f Euro", PRICE);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
