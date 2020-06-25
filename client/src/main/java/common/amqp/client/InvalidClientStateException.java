package common.amqp.client;

/**
 * Used for an invalidate state of the client.
 */
public final class InvalidClientStateException extends Exception {

    /**
     * Constructor for this exception.
     */
    public InvalidClientStateException() {
        super("Used an AmqpClient method in an invalid state.");
    }
}
