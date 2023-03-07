package at.ac.uibk.pm.g06.csaz9837.midterm2.e01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortedTriple<T> {
    private T element1;
    private T element2;
    private T element3;

    public SortedTriple(T element1, T element2, T element3) {
        List<T> listToSort = new ArrayList<>();
        listToSort.add(element1);
        listToSort.add(element2);
        listToSort.add(element3);
        List<T> newList = listToSort.stream().sorted().toList();
        this.element1 = newList.get(0);
        this.element2 = newList.get(1);
        this.element3 = newList.get(2);
    }

    public SortedTriple(T element1, T element2, T element3, Comparator<T> comp) {
        List<T> listToSort = new ArrayList<>();
        listToSort.add(element1);
        listToSort.add(element2);
        listToSort.add(element3);
        List<T> newList = listToSort.stream().sorted(comp).toList();
        this.element1 = newList.get(0);
        this.element2 = newList.get(1);
        this.element3 = newList.get(2);
    }

    public T getElement1() {
        return element1;
    }

    public T getElement2() {
        return element2;
    }

    public T getElement3() {
        return element3;
    }

    public SortedTriple<T> sortWithNewComp(Comparator<T> comp) {
        return new SortedTriple(element1, element2, element3, comp);
    }

    public void reverseOrder() {
        T saveElement1 = getElement1();
        this.element1 = getElement3();
        this.element2 = getElement2();
        this.element3 = saveElement1;
    }

    @Override
    public String toString() {
        return "SortedTriple: " +
                "(" + element1 +
                ", " + element2 +
                ", " + element3 +
                ")";
    }
}
