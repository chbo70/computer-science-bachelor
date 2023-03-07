package at.ac.uibk.pm.exceptions.resourceloader;

/**
 * Exception for resource handling.
 */
class ResourceException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs exception based on given message.
     *
     * @param message text describing exception.
     */
    public ResourceException(String message) {
        super(message);
    }

    /**
     * Constructs exception based on given throwable object.
     *
     * @param t throwable to be used.
     */
    public ResourceException(Throwable t) {
        super(t);
    }
}
