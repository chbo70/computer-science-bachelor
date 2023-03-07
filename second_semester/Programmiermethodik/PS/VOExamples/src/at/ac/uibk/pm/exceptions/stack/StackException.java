package at.ac.uibk.pm.exceptions.stack;

/**
 * Abstract superclass for all exceptions related to stacks.
 */
public abstract class StackException extends Exception {
    private static final long serialVersionUID = -365938557694069058L;

    /**
     * Constructs exception with given string.
     *
     * @param info string holding information about exception.
     */
    protected StackException(String info) {
        super(info);
    }

    /**
     * Default constructor.
     */
    protected StackException() {
    }

}
