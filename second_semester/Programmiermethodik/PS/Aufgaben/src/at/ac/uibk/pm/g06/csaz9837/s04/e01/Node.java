package at.ac.uibk.pm.g06.csaz9837.s04.e01;

public class Node {
    private Node next;
    private final String element;

    public Node(String element) {
        this.element = element;
    }

    public String getElement() {
        return element;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
