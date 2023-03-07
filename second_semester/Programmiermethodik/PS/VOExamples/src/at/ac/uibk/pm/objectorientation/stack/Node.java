package at.ac.uibk.pm.objectorientation.stack;

/**
 * Class holds information about a node.
 */
public class Node {
    private final String value;
    private final Node next;

    public Node(String value, Node next) {
        this.value = value;
        this.next = next;
    }

    /**
     * Returns next node.
     *
     * @return next Node-object.
     */
    public Node getNext() {
        return next;
    }

    /**
     * Returns value stored in current node.
     *
     * @return first Node-object.
     */
    public String getValue() {
        return value;
    }

}
