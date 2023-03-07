package at.ac.uibk.pm.generics.linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class implements a linked list
 *
 * @param <T> generic type of elements contained within the list.
 */
public class LinkedList<T> implements Iterable<T> {

    private final Node root = new Node(null, null);
    private long modificationCounter = 0;

    /**
     * Method increments modification counter.
     */
    private void touch() {
        ++this.modificationCounter;
    }

    /**
     * Method adds the given element elem to the front of the list.
     *
     * @param elem the element to be added.
     */
    public void add(T elem) {
        this.touch();
        this.root.next = new Node(elem, this.root.next);
    }

    /**
     * Method empties the list.
     */
    public void clear() {
        if (this.root.next != null) {
            this.touch();
            this.root.next = null;
        }
    }

    /**
     * Method returns an iterator for the list.
     */
    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    /**
     * Method appends another list to the current list.
     *
     * @param other the list to be added.
     */
    public void append(Iterable<? extends T> other) {
        Node last = this.root;
        while (last.next != null) {
            last = last.next;
        }
        for (T elem : other) {
            this.touch();
            last.next = new Node(elem, null);
            last = last.next;
        }
    }

    /**
     * Returns a string representation of the list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (Iterator<T> iterator = iterator(); iterator.hasNext();) {
            sb.append(iterator.next());
            if (!iterator.hasNext()) {
                break;
            }
            sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }

    /**
     * Inner class implementing iterator on top of linked list.
     */
    private class ListIterator implements Iterator<T> {
        private Node iteratorNode = root.next;
        private final long iteratorModificationCounter = modificationCounter;

        /**
         * Method checks if iterator has more elements, returns true if there are more
         * elements, else false.
         *
         * @return true if there is a next element, else false
         */
        @Override
        public boolean hasNext() {
            if (modificationCounter != iteratorModificationCounter) {
                throw new ConcurrentModificationException();
            }
            return iteratorNode != null;
        }

        /**
         * Method returns next element in iteration.
         *
         * @return next element in linked list
         */
        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            T element = iteratorNode.element;
            iteratorNode = iteratorNode.next;
            return element;
        }

        /**
         * Method removes current element of iteration.
         *
         * @throws UnsupportedOperationException as it is not yet implemented
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Inner class holding information about nodes within the linked list.
     */
    private class Node {
        T element;
        Node next;

        /**
         * Constructs the node with the given element and next element.
         *
         * @param element the content of the node.
         * @param next the next element within the linked list.
         */
        private Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }
    }
}
