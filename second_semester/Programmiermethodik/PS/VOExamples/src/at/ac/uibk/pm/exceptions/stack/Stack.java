package at.ac.uibk.pm.exceptions.stack;

/**
 * Interface provides specification for stack implementation.
 */
public interface Stack {
    /**
     * Pops top element from stack and returns it.
     *
     * @return top element of stack
     * @throws StackEmptyException if stack is already empty
     */
    Object pop() throws StackEmptyException;

    /**
     * Returns the size of the given stack.
     *
     * @return number of elements contained in stack.
     */
    int size();

    /**
     * Pushes given element onto the stack.
     *
     * @param element element to be pushed onto the stack.
     * @throws StackFullException if stack is full and no more elements can be pushed.
     */
    void push(Object element) throws StackFullException;

    /**
     * Pushes given elements onto the stack.
     *
     * @param elements elements to be pushed onto the stack.
     * @throws StackFullException if stack is full and no more elements can be pushed.
     */
    void push(Object[] elements) throws StackFullException;
}
