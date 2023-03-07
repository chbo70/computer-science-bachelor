package at.ac.uibk.pm.g06.csaz9837.s04.e02;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private static final int MAX_STUDENTS = 2;
    private List<String> students = new ArrayList<>();

    public boolean addStudent(String student) throws StudentAlreadyEnrolledException, CourseFullException {

        if (students.size() >= MAX_STUDENTS) {
            throw new CourseFullException(student);
            //return false;
        }
        if (students.contains(student)) {
            throw new StudentAlreadyEnrolledException(student);
            //return false;
        }
        students.add(student);
        return true;
    }
}