package at.ac.uibk.pm.inheritance.stackcontainer;

class LinkedListIterator implements RemovableIterator {

    private Node current;
    private Node returned = null;
    private final Node last;

    LinkedListIterator(Node start, Node last) {
        this.current = start;
        this.last = last;
    }

    /**
     * Method checks if iterator has more elements, returns true if there are
     * more elements, else false.
     *
     * @return true if there is a next element, else false
     */
    @Override
    public boolean hasNext() {
        return this.current != last;
    }

    /**
     * Method returns next element in iteration.
     *
     * @return next element in linked list
     */
    @Override
    public String next() {
        if (!this.hasNext()) {
            return null;
        }
        this.returned = this.current;
        this.current = this.current.getNext();
        return this.returned.getData();
    }

    /**
     * Method removes last element that was returned by next() from underlying
     * data structure.
     */
    @Override
    public void remove() {
        if (this.returned == null) {
            System.err.println("invoke next before remove");
            return;
        }
        Node previous = this.returned.getPrevious();
        Node next = this.returned.getNext();
        previous.setNext(next);
        next.setPrevious(previous);
        this.returned = null;
    }
}
