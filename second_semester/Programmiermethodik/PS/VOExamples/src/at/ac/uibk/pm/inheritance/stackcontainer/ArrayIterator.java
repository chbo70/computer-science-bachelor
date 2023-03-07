package at.ac.uibk.pm.inheritance.stackcontainer;

class ArrayIterator implements Iterator {
    private int position = 0;
    private final int size;
    private final String[] data;

    /**
     * Constructs iterator on given array with given number of elements.
     *
     * @param data array to base the iterator on.
     * @param size number of elements contained in the array data.
     */
    ArrayIterator(String[] data, int size) {
        this.size = size;
        this.data = data;
    }

    /**
     * Method checks if iterator has more elements, returns true if there are
     * more elements, else false.
     *
     * @return true if there is a next element, else false
     */
    @Override
    public boolean hasNext() {
        return this.position < this.size;
    }

    /**
     * Method returns next element in iteration.
     *
     * @return next element
     */
    @Override
    public String next() {
        if (!this.hasNext()) {
            return null; // or throw error message
        }
        String s = this.data[this.position];
        ++this.position;
        return s;
    }
}
