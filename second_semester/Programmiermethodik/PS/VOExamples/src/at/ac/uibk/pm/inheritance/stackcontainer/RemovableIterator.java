package at.ac.uibk.pm.inheritance.stackcontainer;

/**
 * Interface holds specification of a removable iterator for a
 * collection/container, inherits from Iterator interface.
 */
public interface RemovableIterator extends Iterator {

    /**
     * Method removes last element that was returned by next() from underlying
     * data structure.
     */
    void remove();

}