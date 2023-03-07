package at.ac.uibk.pm.objectorientation.rectangle;

/**
 * Class provides means to test rectangle implementation.
 */
public class RectangleApplication {

    /**
     * Main method for rational application
     *
     * @param args not used
     */
    public static void main(String[] args) {
        Rectangle rectangle1 = new Rectangle(20, 3);
        System.out.println("Rectangle instances: " + Rectangle.getInstanceCounter());
        Rectangle rectangle2 = new Rectangle(10, 5);
        System.out.println("Rectangle instances: " + Rectangle.getInstanceCounter());

        rectangle1.printRectangle();
        rectangle2.printRectangle();

        System.out.println("Area rectangle 1: " + rectangle1.getArea());
        System.out.println("Area rectangle 2: " + rectangle2.getArea());

    }
}
