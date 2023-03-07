package at.ac.uibk.pm.generics.stack;

import at.ac.uibk.pm.exceptions.stack.StackEmptyException;

import java.util.Collection;

/**
 * Interface provides specification for stack implementation.
 */
public interface Stack<T> extends Iterable<T> {
    /**
     * Pops top element from stack and returns it.
     *
     * @return top element of stack
     * @throws StackEmptyException if stack is already empty
     */
    T pop() throws StackEmptyException;

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
     */
    void push(T element);

    /**
     * Pushes given elements onto the stack.
     *
     * @param elements elements to be pushed onto the stack.
     */
    void pushAll(Collection<? extends T> elements);

    /**
     * Pops all elements in the stack and adds them to the given collection.
     *
     * @param elements elements of the stack are added to this collection
     */
    void popAll(Collection<? super T> elements);

    /**
     * Checks whether stack is empty.
     *
     * @return true if stack is empty, else false.
     */
    boolean isEmpty();
}
