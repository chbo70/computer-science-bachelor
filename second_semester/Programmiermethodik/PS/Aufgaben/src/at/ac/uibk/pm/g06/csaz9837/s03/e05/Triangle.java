package at.ac.uibk.pm.g06.csaz9837.s03.e05;

public class Triangle extends Colour {
    private final double a;
    private final double b;
    private final double c;
    private final double height_a;

    public Triangle(double a, double b, double c, double height_a, String colour) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.height_a = height_a;
        this.colour = colour;
    }

    public double getArea(){
        return (a * height_a)/2;
    }

    public void getCircumference() {
        circumference = a + b + c;
    }

    @Override
    public void printArea() {
        super.printArea();
        System.out.println("Triangle: " + getArea());
    }

    @Override
    public void printCircumference() {
        super.printCircumference();
        System.out.println("Triangle: " + circumference);
    }
}
