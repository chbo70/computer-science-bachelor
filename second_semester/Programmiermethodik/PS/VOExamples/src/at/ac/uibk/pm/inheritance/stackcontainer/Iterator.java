package at.ac.uibk.pm.inheritance.stackcontainer;

/**
 * Interface holds specification of an iterator for a collection/container.
 */
public interface Iterator {

    /**
     * Method checks if iterator has more elements, returns true if there are
     * more elements, else false.
     *
     * @return true if there is a next element, else false
     */
    boolean hasNext();

    /**
     * Method returns next element in iteration.
     *
     * @return next element
     */
    String next();
}
