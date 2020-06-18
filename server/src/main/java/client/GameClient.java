package client;

import client.config.Destinations;
import client.config.Hosts;
import client.messages.Message;

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

    /**
     * It closes the connection gracefully.
     */
    void disconnect();

    /**
     * It sends a message against a given destination. The fact that
     * the destination is a topic or a queue is transparent to the user.
     * @param message The message to be sent.
     * @param destination The destination against which send the message.
     */
    void sendMessage(Message message, String destination);


    /**
     * Builder used to initialize the communicator class with the proper
     * configuration.
     */
    class RabbitCommunicatorBuilder {
        private final Set<CtxCallback> stdCtxCallbacks = new HashSet<>();
        private String host = Hosts.LOCAL;


        public RabbitCommunicatorBuilder addCallback(final CtxCallback ctxCallback) {
            if (Destinations.isKnownDestination(ctxCallback.getDestination())) {
                stdCtxCallbacks.add(ctxCallback);
            }
            return this;
        }


        public RabbitCommunicatorBuilder addHost(final String host) {
            if (Hosts.isKnownHost(host)) {
                this.host = host;
            }

            return this;
        }


        public GameClient build() {
            return new GameClientImpl(host, stdCtxCallbacks);
        }
    }
}
