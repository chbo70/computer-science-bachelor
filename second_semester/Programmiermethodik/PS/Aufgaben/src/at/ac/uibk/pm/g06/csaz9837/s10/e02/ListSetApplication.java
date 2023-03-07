package at.ac.uibk.pm.g06.csaz9837.s10.e02;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ListSetApplication {
    public static void main(String[] args) {
        ListSet<String> listSet = new ListSet<>();
        listSet.add("a");
        listSet.add("b");
        listSet.add("c");
        listSet.add("d");
        listSet.add("e");

        System.out.println(listSet);

        List<String> listToAdd = new ArrayList<>();
        listToAdd.add("g");
        listToAdd.add("f");

        listSet.addAll(listToAdd);
        System.out.println(listSet);

        System.out.println(listSet.isEmpty());
        System.out.println(listSet.contains("a"));
        System.out.println(listSet.contains("f"));
        System.out.println(listSet.size());

        ListSet<String> listSet3 = listSet.clone1();
        System.out.println(listSet3);

        listSet.removeAll(listToAdd);
        System.out.println(listSet);

    }
}
