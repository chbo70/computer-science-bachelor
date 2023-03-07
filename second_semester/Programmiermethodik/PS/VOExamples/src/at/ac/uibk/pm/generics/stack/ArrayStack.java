package at.ac.uibk.pm.generics.stack;

import java.util.*;

/**
 * Class provides a generic stack implementation with bounded size based on array.
 *
 * @param <T> the datatype of entries to be added to the stack.
 */
public class ArrayStack<T> implements Stack<T> {
    private static final int DEFAULT_SIZE = 16;
    private static final int GROWTH_FACTOR = 2;
    private static final int SHRINKAGE_FACTOR = 4;
    private T[] data;
    private int position = 0;
    private int capacity = DEFAULT_SIZE;
    private long modificationCounter = 0;

    /**
     * Constructs an ArrayStack object of the given size.
     *
     * @param size the number of elements to allocate within array.
     */
    public ArrayStack(int size) {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[size];
        data = result;
    }

    /**
     * Constructs an ArrayStack object of the default size.
     */
    public ArrayStack() {
        this(DEFAULT_SIZE);
    }

    /**
     * Checks whether stack is empty.
     *
     * @return true if stack is empty, else false.
     */
    @Override
    public boolean isEmpty() {
        return position == 0;
    }

    /**
     * Pops top element from stack and returns it.
     *
     * @return top element of stack
     */
    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        --position;
        T element = data[position];
        data[position] = null;
        if (position < capacity / SHRINKAGE_FACTOR && capacity > DEFAULT_SIZE) {
            resize(capacity / GROWTH_FACTOR);
        }
        ++modificationCounter;
        return element;
    }

    /**
     * Pushes given element object onto the stack.
     *
     * @param element object to be pushed onto the stack.
     */
    @Override
    public void push(T element) {
        if (position == capacity) {
            resize(capacity * GROWTH_FACTOR);
        }
        data[position] = element;
        ++position;
        ++modificationCounter;
    }

    /**
     * Pushes given elements onto the stack.
     *
     * @param elements elements to be pushed onto the stack.
     */
    @Override
    public void pushAll(Collection<? extends T> elements) {
        for (T element : elements) {
            push(element);
        }
    }

    /**
     * Pops all elements in the stack and adds them to the given collection.
     *
     * @param elements elements of the stack are added to this collection
     */
    @Override
    public void popAll(Collection<? super T> elements) {
        while (!isEmpty()) {
            elements.add(pop());
        }
    }

    /**
     * Returns the size of the given stack.
     *
     * @return number of elements contained in stack.
     */
    @Override
    public int size() {
        return position;
    }

    /**
     * Method resizes array underlying the stack by creating a new array and then copying old
     * data to new array.
     *
     * @param size new size of array
     */
    private void resize(int size) {
        this.data = Arrays.copyOf(this.data, size);
        this.capacity = size;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {
        private int iteratorPosition = 0;
        private final long iteratorModificationCounter = modificationCounter;

        @Override
        public boolean hasNext() {
            if (iteratorModificationCounter != modificationCounter) {
                throw new ConcurrentModificationException();
            }
            return iteratorPosition < position;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            T element = data[iteratorPosition];
            ++iteratorPosition;
            return element;
        }
    }
}
