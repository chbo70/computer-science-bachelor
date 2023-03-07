package at.ac.uibk.pm.inheritance.stackcontainer;

/**
 * Interface provides specification for stack implementation.
 */
public interface Stack {

    /**
     * Pops top element from stack and returns it.
     *
     * @return top element of stack
     */
    String pop();

    /**
     * Returns the size of the given stack.
     *
     * @return number of elements contained in stack.
     */
    int size();

    /**
     * Pushes given element onto the stack.
     *
     * @param element String to be pushed onto the stack.
     */
    void push(String element);
}
