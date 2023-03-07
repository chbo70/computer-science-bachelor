package at.ac.uibk.pm.g06.csaz9837.examPrep;

public class funcClass implements FuncInterface {

    @Override
    public void foo() {
        System.out.println("foo");
    }

    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
