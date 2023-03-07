package at.ac.uibk.pm.objectorientation.stack;

/**
 * Class provides stack implementation based on a linked list with included size
 * counter.
 */

public class LinkedListStackWithSizeCounter {

    private Node top = null;
    private int size = 0;

    /**
     * Checks whether stack is empty.
     *
     * @return true if stack is empty, else false.
     */
    public boolean isEmpty() {
        return (top == null);
    }

    /**
     * Pops top element from stack and returns it.
     *
     * @return top element of stack
     */
    public String pop() {
        if (top == null) {
            return null;
        }
        String result = top.getValue();
        top = top.getNext();
        --size;
        return result;
    }

    /**
     * Pushes the given element onto the stack.
     *
     * @param element String to be pushed onto the stack.
     */
    public void push(String element) {
        top = new Node(element, top);
        ++size;
    }

    /**
     * Peeks at the top element from stack and returns it.
     *
     * @return top element of stack
     */
    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return top.getValue();
    }

    /**
     * Returns the size of the given stack.
     *
     * @return number of elements contained in stack.
     */
    public int size() {
        return size;
    }

}