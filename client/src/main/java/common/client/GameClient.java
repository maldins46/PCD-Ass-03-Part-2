package common.client;

import common.client.messages.Message;
import common.model.Player;


/**
 * Facade module that handles a connection over the AMQP protocol, with
 * a RabbitMQ server, abstracting from exchange-queue mechanisms typical of
 * AMQP and RabbitMQ, and made to measure of the puzzle project.
 */
public interface GameClient {
    /**
     * It starts the connections, and initializes possible given callbacks.
     */
    void connect();

    /**
     * Add a callback to the client.
     * @param callback The callback that have to be added.
     */
    void addCallback(CtxCallback callback);

    /**
     * Activate listen.
     */
    void listen();

    /**
     * Send a message to the server.
     * @param message The message that we want to send.
     */
    void sendMessageToServer(Message message);

    /**
     * Send a message to a specific player.
     * @param player The player that we want to send a message.
     * @param message The message that we want to the send.
     */
    void sendMessageToPlayer(Player player, Message message);

    /**
     * Send a message to the player or to the server.
     * @param message The message that we want to the send.
     */
    void sendMessageToMatch(Message message);


    /**
     * It closes the connection gracefully.
     */
    void disconnect();


    /**
     * Create an instance of an host.
     * @param host The address of the host.
     * @param isServerModule True if is server, false for client.
     * @return The instance of the host.
     */
    static GameClient of(String host, boolean isServerModule) {
        return new GameClientImpl(host, isServerModule);
    }
}
