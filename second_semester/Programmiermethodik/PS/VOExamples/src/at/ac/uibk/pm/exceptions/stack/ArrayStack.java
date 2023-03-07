package at.ac.uibk.pm.exceptions.stack;

import java.util.Collection;

/**
 * Class provides a stack implementation with bounded size based on array).
 * Implements interfaces Stack and Container.
 */
public class ArrayStack implements Stack {

    private static final int DEFAULT_SIZE = 100;
    private final Object[] data;
    private int position = 0;

    /**
     * Constructs an ArrayStack object of the default size.
     */
    public ArrayStack() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructs n ArrayStack object of the given size.
     *
     * @param size the number of elements to allocate within array.
     */
    public ArrayStack(int size) {
        this.data = new Object[size];
    }

    /**
     * Pushes given element object onto the stack.
     *
     * @param element element to be pushed onto the stack.
     * @throws StackFullException if stack is full and no more elements can be pushed.
     */
    @Override
    public void push(Object element) throws StackFullException {
        if (this.position >= this.data.length) {
            throw new StackFullException(position, data.length);
        }
        this.data[this.position] = element;
        ++this.position;
    }

    /**
     * Pushes given elements onto the stack.
     *
     * @param elements elements to be pushed onto the stack.
     * @throws StackFullException if stack is full and no more elements can be pushed.
     */
    @Override
    public void push(Object[] elements) throws StackFullException {
        for (Object element : elements) {
            push(element);
        }
    }

    /**
     * Returns the size of the given stack.
     *
     * @return number of elements contained in stack.
     */
    @Override
    public int size() {
        return this.position;
    }

    /**
     * Pops top element from stack and returns it.
     *
     * @return top element of stack
     */
    @Override
    public Object pop() throws StackEmptyException {
        if (this.position <= 0) {
            throw new StackEmptyException(position);
        }
        --this.position;
        Object element = this.data[this.position];
        this.data[this.position] = null;
        return element;
    }

}