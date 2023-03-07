package at.ac.uibk.pm.g06.csaz9837.s05.e04;

public class OrangeJuice extends Drink {
    private static final String NAME = "Orange Juice";
    private static final double PRICE = 1.90;
    private static final int UPPER_BOUND = 8;
    private static final int LOWER_BOUND = 1;

    public OrangeJuice() {
        super(NAME, UPPER_BOUND, LOWER_BOUND, PRICE);
    }

    @Override
    public String printPrice() {
        return String.format("Orange Juice \t %.2f Euro", PRICE);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
