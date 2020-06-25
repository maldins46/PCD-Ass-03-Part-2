package common.amqp.client;

import common.amqp.callback.PuzzleServiceCallback;
import common.amqp.messages.PuzzleServiceMsg;
import common.model.Player;

/**
 * It describes the AMQP client used by the puzzle service. Gives the
 * possibility to communicate with players, directly or in a multicast
 * fashion.
 */
public interface PuzzleServiceClient extends AmqpClient {

    /**
     * Add a callback to the client.
     * @param callback The callback that have to be added.
     */
    void addCallback(PuzzleServiceCallback callback);


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
