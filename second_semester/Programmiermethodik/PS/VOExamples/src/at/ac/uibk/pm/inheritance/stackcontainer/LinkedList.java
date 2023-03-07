package at.ac.uibk.pm.inheritance.stackcontainer;

/**
 * Class implements a linked list
 */
public class LinkedList implements Container {

    private final Node dummyHead;
    private final Node dummyTail;

    /**
     * Constructs an empty linked list.
     */
    public LinkedList() {
        Node head = new Node(null, null, null);
        Node tail = new Node(null, null, null);
        this.dummyHead = head;
        this.dummyTail = tail;
        head.setNext(tail);
        tail.setPrevious(head);
    }

    /**
     * Method adds the given element elem after the first element of the list.
     *
     * @param element the element to be added.
     */
    @Override
    public void addElement(String element) {
        Node n = new Node(element, this.dummyHead, this.dummyHead.getNext());
        this.dummyHead.setNext(n);
        n.getNext().setPrevious(n);
    }

    /**
     * Method returns number of elements within list.
     *
     * @return number of elements in list.
     */
    @Override
    public int size() {
        Node currentPosition = this.dummyHead.getNext();
        int count = 0;
        while (currentPosition != this.dummyTail) {
            currentPosition = currentPosition.getNext();
            ++count;
        }
        return count;
    }

    /**
     * Returns a RemovableIterator on the linked list.
     *
     * @return removeableIterator
     */
    @Override
    public RemovableIterator iterator() {
        return new LinkedListIterator(this.dummyHead.getNext(), this.dummyTail);
    }

}
