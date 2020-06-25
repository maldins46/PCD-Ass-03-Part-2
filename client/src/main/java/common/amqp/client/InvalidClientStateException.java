package common.amqp.client;

/**
 * Exception thrown when a method of the client is called in an invalid state.
 */
public final class InvalidClientStateException extends Exception {
    public InvalidClientStateException() {
        super("Used an AmqpClient method in an invalid state.");
    }
}
