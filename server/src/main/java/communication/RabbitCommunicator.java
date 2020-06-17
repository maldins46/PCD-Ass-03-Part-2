package communication;

import communication.messages.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * Facade module that handles a connection over the AMQP protocol, with
 * a RabbitMQ server. It gives methods to start the connection, send
 * messages and handle callbacks on receive.
 */
public interface RabbitCommunicator {
    void connect();

    void disconnect();

    void sendMessage(Message message, Destinations destination);


    /**
     * Standard builder class to ...
     */
    class RabbitCommunicatorBuilder {
        private final Map<MessageTypes, Callback> serverQueueCallbacks = new HashMap<>();
        private final Map<MessageTypes, Callback> matchTopicCallbacks = new HashMap<>();
        private Hosts host;


        public RabbitCommunicatorBuilder addCallback(final Callback callback, final Destinations destination) {
            switch (destination) {
                case SERVER_QUEUE_NAME:
                    serverQueueCallbacks.put(callback.getMessageType(), callback);
                    break;

                case MATCH_TOPIC_NAME:
                    matchTopicCallbacks.put(callback.getMessageType(), callback);
                    break;

                default:
                    break;
            }

            return this;
        }


        public RabbitCommunicatorBuilder addHost(final Hosts host) {
            switch (host) {
                case LOCAL:
                    this.host = Hosts.LOCAL;
                    break;

                case INTERNAL_RABBIT:
                    this.host = Hosts.INTERNAL_RABBIT;
                    break;

                default:
                    this.host = Hosts.LOCAL;
                    break;
            }

            return this;
        }


        public RabbitCommunicator build() {
            return new RabbitCommunicatorImpl(host, serverQueueCallbacks, matchTopicCallbacks);
        }
    }
}
