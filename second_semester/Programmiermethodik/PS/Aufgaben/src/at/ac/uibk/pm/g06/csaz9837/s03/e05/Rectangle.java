package at.ac.uibk.pm.g06.csaz9837.s03.e05;

public class Rectangle extends Colour {
    private final double width;
    private final double height;

    public Rectangle(double width, double height, String colour) {
        this.width = width;
        this.height = height;
        this.colour = colour;
    }

    public double getArea(){
        return height * width;
    }

    public void getCircumference(){
        circumference = 2 * height + 2 * width;
    }

    @Override
    public void printArea() {
        super.printArea();
        System.out.println("Rectangle: " + getArea());
    }

    @Override
    public void printCircumference() {
        super.printCircumference();
        System.out.println("Rectangle: " + circumference);
    }
}
