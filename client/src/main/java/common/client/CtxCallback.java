package common.client;

import common.client.messages.Message;

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
     * The action to be executed when message arrives.
     * @param message The received message. Generally, it can be casted
     *                to the specific message type without problems.
     */
    void execute(Message message);
}
