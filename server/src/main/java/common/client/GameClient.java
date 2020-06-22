package common.client;

import common.client.config.Destinations;
import common.client.config.Hosts;
import common.client.messages.Message;

import java.util.HashSet;
import java.util.Set;


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

    /**
     * It sends a message against a given destination. The fact that
     * the destination is a topic or a queue is transparent to the user.
     * @param message The message to be sent.
     * @param destination The destination against which send the message.
     */
    void sendMessage(Message message, String destination);

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
