package communication;

import communication.messages.Message;

import java.util.HashSet;
import java.util.Set;


/**
 * Facade module that handles a connection over the AMQP protocol, with
 * a RabbitMQ server. It gives methods to start the connection, send
 * messages and handle callbacks on receive.
 */
public interface RabbitCommunicator {
    /**
     * todo
     */
    void connect();

    /**
     * todo
     */
    void disconnect();

    /**
     * todo
     */
    void sendMessage(Message message, String destination);


    /**
     * todo
     */
    class RabbitCommunicatorBuilder {
        private final Set<MicroCallback> microCallbacks = new HashSet<>();
        private String host = Hosts.LOCAL;


        public RabbitCommunicatorBuilder addCallback(final MicroCallback microCallback) {
            if (Destinations.isKnownDestination(microCallback.getDestination()))
                microCallbacks.add(microCallback);
            return this;
        }


        public RabbitCommunicatorBuilder addHost(final String host) {
            if (Hosts.isKnownHost(host)) {
                this.host = host;
            }

            return this;
        }


        public RabbitCommunicator build() {
            return new RabbitCommunicatorImpl(host, microCallbacks);
        }
    }
}
