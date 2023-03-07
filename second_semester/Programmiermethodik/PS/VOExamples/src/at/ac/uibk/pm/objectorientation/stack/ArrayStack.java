package at.ac.uibk.pm.objectorientation.stack;

import java.util.Arrays;

/**
 * Class provides stack implementation based on an array.
 */

public class ArrayStack {
    private static final int INITIAL_CAPACITY = 16;
    private static final int GROWTH_FACTOR = 2;
    private static final int SHRINKAGE_FACTOR = 4;
    private int capacity = INITIAL_CAPACITY;
    private String[] data = new String[capacity];
    private int position = 0;

    /**
     * Pops top element from stack and returns it.
     *
     * @return top element of stack
     */
    public String pop() {
        if (isEmpty()) {
            return null;
        }
        --position;
        String result = data[position];
        data[position] = null;
        if (position < capacity / SHRINKAGE_FACTOR && capacity > INITIAL_CAPACITY) {
            resize(capacity / GROWTH_FACTOR);
        }
        return result;
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
        return data[position - 1];
    }

    /**
     * Pushes given element onto the stack.
     *
     * @param element String to be pushed onto the stack.
     */
    public void push(String element) {
        if (position == capacity) {
            resize(capacity * GROWTH_FACTOR);
        }
        data[position] = element;
        ++position;
    }

    /**
     * Checks whether stack is empty.
     *
     * @return true if stack is empty, else false.
     */
    public boolean isEmpty() {
        return (position <= 0);
    }

    /**
     * Returns the size of the given stack.
     *
     * @return number of elements contained in stack.
     */
    public int size() {
        return position;
    }

    /**
     * Method resizes array underlying the stack by creating a new array and
     * then copying old data to new array.
     *
     * @param capacity new capacity of array
     */
    private void resize(int capacity) {
        data = Arrays.copyOf(data, capacity);
        this.capacity = capacity;
    }
}
