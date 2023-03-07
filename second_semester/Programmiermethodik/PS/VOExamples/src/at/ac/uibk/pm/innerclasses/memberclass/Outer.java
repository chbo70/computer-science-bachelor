package at.ac.uibk.pm.innerclasses.memberclass;

public class Outer {
    private final String name = "Outer";

    private class Inner {
        private String name;

        private String getQualifiedName() {
            return Outer.this.name + "." + this.name;
        }
    }
    public void createAndPrintInner(final String innerName) {
        final Inner inner = new Inner();
        inner.name = innerName;
        System.out.println(inner.getQualifiedName());
    }
}