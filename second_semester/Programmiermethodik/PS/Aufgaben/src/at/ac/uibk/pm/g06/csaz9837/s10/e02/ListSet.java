package at.ac.uibk.pm.g06.csaz9837.s10.e02;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListSet<T> {

    private List<T> elements;
    //null redundant because of the constructor
    private int s = 0;

    //added this.
    public ListSet() {
        this.elements = new ArrayList<>();
    }

    //constructo which takes a list and copies it to the elements list.

    public ListSet(List<T> elementstoAdd) {
        this.elements = new ArrayList<>(elementstoAdd);
    }

    //add has the wrong return type
    /*public boolean add(T element) {
        //throw exception if element is null
        if (element == null) {
            throw new IllegalArgumentException("element is null");
        }
        if (elements.contains(element)) {
            return false;
        } else {
            elements.add(element);
            s++;
            return true;
        }
    }*/

    public void add(T element) {
        if (!elements.contains(element)) {
            //removed cast since the type is already generic
            elements.add(element);
            s++;
        }
    }

    public void addAll(Collection<T> elementsToAdd) {
        elements.addAll(elementsToAdd);
        //removed this. because you dont want to add the same listset to itself
        s += elementsToAdd.size();
    }

    //method for checking if the set is empty

    public boolean isEmpty() {
        //changed to return boolean of the counter for length of the list
        return s == 0;
    }

    //changed type to more general T instead of Object
    public boolean contains(T element) {
        for (T t : elements) {
            if (element == t) {
                return true;
            }
        }
        return false;
    }

    //use stringbuilder since + is overloaded
    /*public String toString() {
        StringBuilder sb = new StringBuilder();

        for (T t : elements) {
            sb.append(t);
            sb.append(", ");
        }
        return sb.toString();
    }
    */
    public String toString() {
        String result = "";
        for (int i = 0; i < size(); i++) {
            //why remove the element at index i? use get
            result += elements.get(i);
            result += " ";
        }
        return result;
    }
    public ListSet<T> clone1() {
        ListSet<T> clone = new ListSet<>(new ArrayList<>(elements));
        clone.s = s;
        return clone;
    }

    public void removeAll(Collection<T> toRemove) {
        elements.removeAll(toRemove);
        s -= toRemove.size();
    }

    public int size() {
        return s;
    }


}
