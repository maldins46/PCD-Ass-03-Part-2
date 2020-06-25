package common.amqp.callback;

import common.amqp.messages.PlayerMsg;

/**
 * An action to be executed when a message of a specific type,
 * from a specific destination, is received.
 */
public interface PuzzleServiceCallback extends Callback {

    /**
     * The type of the received message.
     */
    Class<? extends PlayerMsg> getMessageType();
}
