package at.ac.uibk.pm.g06.csaz9837.examPrep;

public interface FuncInterface {
    default int add(int a, int b) {
        return a + b;
    }
    void foo();
}
