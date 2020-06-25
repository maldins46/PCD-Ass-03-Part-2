package common.amqp.client;

import common.amqp.CtxCallback;

interface AmqpClient {
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
     * It closes the connection gracefully.
     */
    void disconnect();


    /**
     * Checks whether connection is correctly set.
     * @return True if the connection is correctly set, false otherwise.
     */
    boolean isConnected();


    /**
     * Checks whether the client is consuming in the personal queue.
     * @return True if it is listening, false otherwise.
     */
    boolean isListening();


    String getPersonalQueueName();
}
