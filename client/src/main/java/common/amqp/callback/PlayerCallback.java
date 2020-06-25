package common.amqp.callback;

import common.amqp.messages.Message;
import common.amqp.messages.PuzzleServiceMsg;

public interface PlayerCallback extends Callback {

    /**
     * The type of the received message.
     */
    Class<? extends PuzzleServiceMsg> getMessageType();
}
