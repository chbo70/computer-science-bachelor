package at.ac.uibk.pm.comparable.rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RectangleApplication {
    public static void main(String[] args) {
        List<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(new Rectangle(10, 10));
        rectangles.add(new Rectangle(10, 4));
        rectangles.add(new Rectangle(20, 4));

        Collections.sort(rectangles);
        System.out.println(rectangles);

    }
}
