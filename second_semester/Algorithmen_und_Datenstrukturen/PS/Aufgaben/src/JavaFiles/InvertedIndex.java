package JavaFiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class InvertedIndex {
    public static void main(String[] args) {
        String test = """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                Etiam ullamcorper consectetur orci, vel vehicula magna euismod nec.
                Etiam lacus felis, porttitor non lorem in, posuere rutrum ipsum.
                Etiam varius pellentesque sapien tempus finibus.
                Vestibulum rutrum, lacus et egestas faucibus, justo velit dignissim purus, a tempor ex velit at felis.
                Suspendisse et libero feugiat, vestibulum ex a, volutpat neque.
                In et molestie enim.
                Suspendisse tincidunt fringilla libero, ut hendrerit magna sagittis nec.
                Cras sodales augue dui, vitae rutrum ipsum consectetur eget.
                Aliquam erat volutpat.
                Vestibulum sed laoreet ante.
                Quisque hendrerit tincidunt sodales.
                Vivamus vel eros sodales, feugiat lectus et, vehicula ante.
                """;
        //invertedIndex(test);
        System.out.println(invertedIndex(test));

    }

    //think of an appropriate return type
    public static Map invertedIndex(String string) {
        Map<String, ArrayList<Integer>> hashMap = new Hashtable<>();
        String[] stringByLine = string.split("\n");

        Integer rowCounter = 1;

        return hashMap;
    }
}
