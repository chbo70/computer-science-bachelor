package at.ac.uibk.pm.objectorientation.stack;

/**
 * Class provides stack implementation based on a linked list.
 */
public class LinkedListStack {

    private Node top = null;

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
        if (isEmpty()) {
            return null;
        }
        String result = top.getValue();
        top = top.getNext();
        return result;
    }

    /**
     * Pushes the given element onto the stack.
     *
     * @param element String to be pushed onto the stack.
     */
    public void push(String element) {
        top = new Node(element, top);
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
        int size = 0;
        Node pos = top;
        while (pos != null) {
            ++size;
            pos = pos.getNext();
        }
        return size;
    }
}