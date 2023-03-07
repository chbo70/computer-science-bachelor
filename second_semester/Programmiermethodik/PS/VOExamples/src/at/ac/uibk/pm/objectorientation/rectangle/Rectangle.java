package at.ac.uibk.pm.objectorientation.rectangle;

public class Rectangle {

    private static int instanceCounter;
    private int width;
    private int length;

    /**
     * Constructs a rectangle with given length and width.
     *
     * @param width  of rectangle to be created.
     * @param length of rectangle to be created.
     */
    public Rectangle(int width, int length) {
        this.width = width;
        this.length = length;
        ++instanceCounter;
    }

    /**
     * Constructs a rectangle with the same side length for all sides.
     *
     * @param sideLength of rectangle to be created.
     */
    public Rectangle(int sideLength) {
        this(sideLength, sideLength);
    }


    /**
     * Constructs a rectangle.
     */
    public Rectangle() {
        ++instanceCounter;
    }

    /**
     * Returns number of instantiated objects of current class.
     *
     * @return number of instantiated objects
     */
    public static int getInstanceCounter() {
        return instanceCounter;
    }

    /**
     * Returns width of rectangle.
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }


    /**
     * Sets width of rectangle.
     *
     * @param width Width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns length of rectangle.
     *
     * @return length
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets length of rectangle.
     *
     * @param length Length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Prints characteristics of rectangle.
     */
    public void printRectangle() {
        System.out.println("Rectangle width: " + width + ", length: " + length);
    }

    /**
     * Returns computed area for rectangle.
     *
     * @return area
     */
    public int getArea() {
        return width * length;
    }
}
