package at.ac.uibk.pm.g06.csaz9837.s03.e05;

public class Circle extends Colour{
    private final double radius;

    public Circle(double radius, String colour) {
        this.radius = radius;
        this.colour = colour;
    }

    public double getArea(){return radius * radius * Math.PI;}

    public void getCircumference() {
        circumference = 2 * radius * Math.PI;
    }

    @Override
    public void printArea() {
        super.printArea();
        System.out.println("Circle: " + getArea());
    }

    @Override
    public void printCircumference() {
        super.printCircumference();
        System.out.println("Circle: " + circumference);
    }
}
