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

    void addCallback(CtxCallback callback);

    void listen();

    void sendMessageToServer(Message message);

    void sendMessageToPlayer(Player player, Message message);

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
