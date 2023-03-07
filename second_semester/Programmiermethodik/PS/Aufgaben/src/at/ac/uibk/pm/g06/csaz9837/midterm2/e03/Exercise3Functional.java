package at.ac.uibk.pm.g06.csaz9837.midterm2.e03;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Exercise3Functional implements Exercise3Interface {

    @Override
    public List<Integer> methodA(List<Integer> list) {
        //removes doubles
        return list.stream().distinct().toList();
    }

    @Override
    public int methodB(List<List<Integer>> lists) {
        //returns number of digits
        return lists.stream().mapToInt(List::size).sum();
    }

    @Override
    public Optional<String> methodC(List<String> list) {
        //comparing each string and returns the longest one using reduce
        return list.stream().reduce((a, b) -> a.length() > b.length() ? a : b);
        //return list.stream().max(Comparator.comparingInt(String::length));
    }
}
