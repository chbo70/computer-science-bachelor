package at.ac.uibk.pm.inheritance.stackcontainer;

/**
 * Abstract base class for stack implementation.
 */
public abstract class DefaultStack implements Stack {
    /**
     * Method provides a default implementation for peek functionality (return
     * content of next element on stack without popping it).
     *
     * @return content of next element on stack
     */
    public String peek() {
        if (this.isEmpty()) {
            return null;
        }
        String o = this.pop();
        this.push(o);
        return o;
    }

    /**
     * Pops top element from stack and returns it.
     *
     * @return top element of stack
     */
    @Override
    public abstract String pop();

    /**
     * Checks whether stack is empty.
     *
     * @return true if stack is empty, else false.
     */
    public abstract boolean isEmpty();

    /**
     * Pushes given element obj onto the stack.
     *
     * @param element String to be pushed onto the stack.
     */
    @Override
    public abstract void push(String element);
}
