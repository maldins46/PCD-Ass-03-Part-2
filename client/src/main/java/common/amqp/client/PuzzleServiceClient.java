package common.amqp.client;

import common.amqp.messages.PuzzleServiceMsg;
import common.model.Player;

/**
 * It describes the AMQP client used by the puzzle service, to communicate with
 * the broker service.
 */
public interface PuzzleServiceClient extends AmqpClient {
    /**
     * Sends a message to a specific player.
     * @param player The player to which send the message.
     * @param message The message to send.
     */
    void sendMessageToPlayer(Player player, PuzzleServiceMsg message);


    /**
     * Sends a message to all players, using the match topic.
     * @param message The message to send.
     */
    void sendMessageToMatch(PuzzleServiceMsg message);
}
