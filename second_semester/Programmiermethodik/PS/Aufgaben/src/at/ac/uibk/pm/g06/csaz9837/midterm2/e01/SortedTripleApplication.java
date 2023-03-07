package at.ac.uibk.pm.g06.csaz9837.midterm2.e01;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SortedTripleApplication {

    public static void main(String[] args) {
        SortedTriple<String> tripleString = new SortedTriple<>("Buli", "Biba", "Zamantha");
        System.out.println(tripleString);
        tripleString.reverseOrder();
        System.out.println(tripleString);
        Comparator<String> comp = Comparator.comparingInt(String::length);
        System.out.println(tripleString.sortWithNewComp(comp));
    }
}
