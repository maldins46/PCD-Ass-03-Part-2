package common.amqp.client;

import common.amqp.callback.PlayerCallback;
import common.amqp.messages.PlayerMsg;

/**
 * It describes the AMQP client used by the player module. Gives the
 * possibility to communicate with the puzzle service.
 */
public interface PlayerClient extends AmqpClient {

    /**
     * Add a callback to the client.
     * @param callback The callback that have to be added.
     */
    void addCallback(PlayerCallback callback);


    /**
     * Send an instruction in the puzzle service queue.
     * @param message The message to send.
     */
    void sendMessageToPuzzleService(PlayerMsg message);
}
