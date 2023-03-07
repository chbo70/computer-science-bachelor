package at.ac.uibk.pm.g06.csaz9837.s10.e04;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class ExerciseApplication {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("java", "pm", "intellij", "c", "programming");

        Comparator<String> comp = Comparator.comparingInt(String::length);
        List<String> list = LeaveOutCollector.leaveOut(stream, 2, comp);
        list.forEach(System.out::println);
    }
}
