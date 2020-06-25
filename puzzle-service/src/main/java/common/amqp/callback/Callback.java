package common.amqp.callback;

import common.amqp.messages.Message;

public interface Callback {

    /**
     * The action to be executed when message arrives.
     * @param message The received message. Generally, it can be casted
     *                to the specific message type without problems.
     */
    void execute(Message message);


    /**
     * The type of the received message.
     */
    Class<? extends Message> getMessageType();
}
