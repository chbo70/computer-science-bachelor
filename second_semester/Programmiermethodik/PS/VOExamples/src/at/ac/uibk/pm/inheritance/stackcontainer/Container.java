package at.ac.uibk.pm.inheritance.stackcontainer;

/**
 * Interface provides specification for data container.
 */
public interface Container {

    /**
     * Returns the number of elements within the container.
     *
     * @return number of elements within container.
     */
    int size();

    /**
     * Adds a new element to the container.
     *
     * @param element the element to be added.
     */
    void addElement(String element);

    /**
     * Returns an iterator on top of the current container.
     *
     * @return Iterator
     */
    Iterator iterator();
}
