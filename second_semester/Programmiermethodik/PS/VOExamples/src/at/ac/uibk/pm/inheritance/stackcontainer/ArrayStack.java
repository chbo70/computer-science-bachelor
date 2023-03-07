package at.ac.uibk.pm.inheritance.stackcontainer;

/**
 * Class provides a stack implementation with bounded size based on array).
 * Implements interfaces Stack and Container.
 */
public class ArrayStack implements Stack, Container {

    private final String[] data;
    private int position = 0;

    private static final int DEFAULT_SIZE = 100;

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
        this.data = new String[size];
    }

    /**
     * Pushes given element object onto the stack.
     *
     * @param element object to be pushed onto the stack.
     */
    @Override
    public void push(String element) {
        if (this.position >= this.data.length) {
            System.err.println("could not insert");
            return;
        }
        this.data[this.position] = element;
        ++this.position;
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
    public String pop() {
        if (this.position <= 0) {
            return null; // or throw error
        }
        --this.position;
        String s = this.data[this.position];
        this.data[this.position] = null;
        return s;
    }

    /**
     * Adds element to the stack, i.e., pops element on stack.
     *
     * @param element element to be added.
     */
    @Override
    public void addElement(String element) {
        this.push(element);
    }

    /**
     * Returns iterator on given ArrayStack.
     *
     * @return iterator
     */
    @Override
    public Iterator iterator() {
        return new ArrayIterator(this.data, this.position);
    }

}
