package at.ac.uibk.pm.g06.csaz9837.s04.e02;

public class StudentAlreadyEnrolledException extends Exception {
    private String student;

    public StudentAlreadyEnrolledException(String student) {
        this.student = student;
    }

    public String getStudent() {
        return student;
    }
}
