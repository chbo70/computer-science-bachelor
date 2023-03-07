package at.ac.uibk.pm.inheritance.stackcontainer;

/**
 * Class provides a node for a linked list
 */
class Node {

    private final String data;
    private Node previous;
    private Node next;

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public String getData() {
        return data;
    }

    /**
     * Constructs a node with given information
     *
     * @param data content of the node
     * @param prev previous element within linked list
     * @param next next element within linked list
     */
    Node(String data, Node prev, Node next) {
        this.data = data;
        this.previous = prev;
        this.next = next;
    }
}