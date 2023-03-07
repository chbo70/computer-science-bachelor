package at.ac.uibk.pm.csaz9837.exam1.e01;

import java.util.*;

public class FunctionalJava {
    public long methodA(List<String> values){
        return values.stream().mapToLong(String::length).sum();
    }

    public <E extends Comparable<? super E>> List<E> methodB(List<E> values, E e){
        return values.stream().filter(element -> e.compareTo(element) > 0).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public <E> Map<E, Long> methodC(List<E> values){
        return values.stream().collect(HashMap::new, (map, value) -> map.put(value, map.getOrDefault(value, 0L) + 1), HashMap::putAll);
    }
}
