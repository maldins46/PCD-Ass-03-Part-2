package common.client;

/**
 * Used for an invalidate state of the client.
 */
public final class InvalidClientStateException extends Exception {

    /**
     * Constructor for this exception.
     */
    public InvalidClientStateException() {
        super("Used a GameClient method in an invalid state.");
    }
}
