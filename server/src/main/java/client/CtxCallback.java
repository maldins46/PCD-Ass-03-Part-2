package client;

import client.messages.Message;

/**
 * An action to be executed when a message of a specific type,
 * from a specific destination, is received.
 */
public interface CtxCallback {
    /**
     * The type of the received message.
     */
    Class<? extends Message> getMessageType();

    /**
     * The destination from which the message has been received.
     */
    String getDestination();

    /**
     * The action to be executed when message arrives.
     * @param message The received message. Generally, it can be casted
     *                to the specific message type without problems.
     */
    void execute(Message message);
}
