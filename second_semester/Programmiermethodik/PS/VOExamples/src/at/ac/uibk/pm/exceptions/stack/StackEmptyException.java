package at.ac.uibk.pm.exceptions.stack;

/**
 * Exception may be thrown if stack is empty and pop is requested.
 */
public class StackEmptyException extends StackException {
    private static final long serialVersionUID = -418845317032305047L;
    private final int elements;

    /**
     * Constructs a StackEmptyException.
     *
     * @param elements number of elements already in the stack
     */
    public StackEmptyException(int elements) {
        super(String.format("Expected non-empty stack but got stack with %d elements.", elements));
        this.elements = elements;
    }

    public int getElements() {
        return elements;
    }
}