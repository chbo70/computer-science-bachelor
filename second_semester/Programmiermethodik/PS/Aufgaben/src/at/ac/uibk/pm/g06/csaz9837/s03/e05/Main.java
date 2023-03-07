package at.ac.uibk.pm.g06.csaz9837.s03.e05;

public class Main {
    public static void main(String[] args) {

        Shape rectangle = new Rectangle(2,3, "Red");
        rectangle.getArea();
        rectangle.getCircumference();
        rectangle.getColour();
        rectangle.printArea();
        rectangle.printCircumference();
        rectangle.printColour();

        System.out.println("--------------------------------------------------------------------");

        Shape circle = new Circle(2.0, "Yellow");
        circle.getArea();
        circle.getCircumference();
        circle.getColour();
        circle.printArea();
        circle.printCircumference();
        circle.printColour();

        System.out.println("--------------------------------------------------------------------");

        Shape triangle = new Triangle(2.0,2.0,3.0,4.0, "Blue");
        triangle.getArea();
        triangle.printArea();
        triangle.getColour();
        triangle.getCircumference();
        triangle.printCircumference();
        triangle.printColour();

        System.out.println("--------------------------------------------------------------------");
    }
}
