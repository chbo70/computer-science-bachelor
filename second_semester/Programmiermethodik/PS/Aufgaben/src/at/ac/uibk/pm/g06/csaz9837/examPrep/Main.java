package at.ac.uibk.pm.g06.csaz9837.examPrep;

public class Main {
    public static void main(String[] args) {
        ClassA o1 = new ClassB();
        ClassA o2 = new ClassC();
        ClassB o3 = new ClassC();
        short s = 2;
        float f = 3;
        int v1 = o2.value;
        int v2 = o3.value;

        o1.m(s);
        o2.m(s);
        o3.m(s);

        o1.m(f);
        o2.m(f);
        o3.m(f);
    }
}
