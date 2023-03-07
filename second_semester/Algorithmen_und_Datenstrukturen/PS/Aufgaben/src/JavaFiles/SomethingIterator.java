package JavaFiles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SomethingIterator {
    public static void doSomething(List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next % 2 == 1) {
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) {
        //create new array list
        List<Integer> list = new ArrayList<>();
        //add elements to array list
        list.add(46);
        list.add(64);
        list.add(73);
        list.add(9);
        list.add(91);
        list.add(47);
        list.add(76);
        doSomething(list);
        System.out.println(list);

    }
}
