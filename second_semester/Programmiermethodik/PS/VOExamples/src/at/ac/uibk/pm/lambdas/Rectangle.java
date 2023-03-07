package at.ac.uibk.pm.lambdas;

import java.util.Comparator;
import java.util.Objects;

import static java.util.Comparator.comparingInt;

public class Rectangle implements Comparable<Rectangle> {

    private static final Comparator<Rectangle> COMPARATOR =
            comparingInt((Rectangle r) -> r.width)
                    .thenComparingInt(r -> r.length);
    private int width;
    private int length;

    /**
     * Constructs a rectangle with given length and width.
     *
     * @param width  of rectangle to be created.
     * @param length of rectangle to be created.
     */
    public Rectangle(int width, int length) {
        validateLength(length);
        validateLength(width);
        this.width = width;
        this.length = length;
    }

    /**
     * Constructs a rectangle.
     */
    public Rectangle() {

    }

    /**
     * Returns width of rectangle.
     *
     * @return width
     */

    public int getWidth() {
        return this.width;
    }

    /**
     * Sets width of rectangle.
     *
     * @param width Width
     */
    public void setWidth(int width) {
        validateWidth(width);
        this.width = width;
    }

    private void validateWidth(int width) {
        if (width < 0) {
            throw new IllegalArgumentException(
                    String.format("Expected non-negative width but got %d.", width));
        }
    }

    /**
     * Returns length of rectangle.
     *
     * @return length
     */

    public int getLength() {
        return this.length;
    }

    /**
     * Sets length of rectangle.
     *
     * @param length Length
     */
    public void setLength(int length) {
        validateLength(length);
        this.length = length;

    }

    private void validateLength(int length) {
        if (length < 0) {
            throw new IllegalArgumentException(String.format("Expected non-negative length but " +
                    "got %d.", length));
        }
    }

    /**
     * Returns computed area for rectangle.
     *
     * @return area
     */
    public int getArea() {
        return this.width * this.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle)) return false;
        Rectangle rectangle = (Rectangle) o;
        return width == rectangle.width && length == rectangle.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, length);
    }

    @Override
    public String toString() {
        return String.format("Rectangle{width=%d, length=%d}", width, length);
    }

    @Override
    public int compareTo(Rectangle o) {
        return COMPARATOR.compare(this, o);
    }

}
