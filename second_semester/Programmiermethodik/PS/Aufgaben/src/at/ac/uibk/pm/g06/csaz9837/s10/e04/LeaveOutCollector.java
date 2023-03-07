package at.ac.uibk.pm.g06.csaz9837.s10.e04;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeaveOutCollector<T> {
    public static <T> List<T> leaveOut(Stream<T> stream, int numberOfElementsToLeaveOut, Comparator<T> comparator) {
        List<T> list = stream.sorted(comparator).collect(Collectors.toList());

        //remove the first numberOfElementsToLeaveOut elements from the list
        if (numberOfElementsToLeaveOut > 0) {
            list.subList(0, numberOfElementsToLeaveOut).clear();
        }

        //remove the last numberOfElementsToLeaveOut elements from the list
        if (numberOfElementsToLeaveOut > 0) {
            list.subList(list.size() - numberOfElementsToLeaveOut, list.size()).clear();
        }
        return list;
    }
}
