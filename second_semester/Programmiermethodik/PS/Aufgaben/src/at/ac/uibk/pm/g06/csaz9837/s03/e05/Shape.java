package at.ac.uibk.pm.g06.csaz9837.s03.e05;

public abstract class Shape {
    protected double area;
    protected double circumference;

    // getter sollten immer etwas zurückgeben!!!

    public abstract double getArea();
    public void getCircumference(){};
    public void getColour(){};

    public void printColour(){System.out.print("Background Colour: ");};
    public void printArea(){
        System.out.print("Area of ");
    };
    public void printCircumference(){
        System.out.print("Circumference of ");
    };
}
