package at.ac.uibk.pm.g06.csaz9837.s03.e05;

public abstract class Colour extends Shape {
    protected String colour;

    public void getColour() {
        this.colour = colour;
    }

    @Override
    public void printColour() {
        super.printColour();
        System.out.println(colour);
    }
}
