package common.amqp.client;

import common.amqp.messages.PlayerMsg;

/**
 * It describes the AMQP client used by the 'client' instances, i.e. players.
 */
public interface PlayerClient extends AmqpClient {
    /**
     * Send an instruction in the puzzle service queue.
     * @param message The message to send.
     */
    void sendMessageToPuzzleService(PlayerMsg message);
}
