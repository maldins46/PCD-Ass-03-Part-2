package common.amqp.client;

import com.google.gson.Gson;
import com.rabbitmq.client.*;
import common.amqp.callback.Callback;

import common.amqp.config.Hosts;
import common.amqp.config.MessageTypes;
import common.amqp.messages.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

/**
 * Implementation of common features of the AMQP clients used
 * in the project. All client types shares some core behavior.
 */
public abstract class GenericAmqpClient implements AmqpClient {

    /**
     * The logger gives some facility for debug purposes.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericAmqpClient.class);

    /**
     * The broker host. "Localhost" is the standard used for tests.
     */
    private String host = Hosts.LOCAL;

    /**
     * The queue that identifies the client. In the project mechanism,
     * every client uses a single queue, that acts as a mailbox.
     */
    private String personalQueue;

    /**
     * Callbacks associated to each message received. Depending on the
     * message type, the callback executed is different.
     */
    private final Set<Callback> callbacks = new HashSet<>();

    /**
     * The connection AMQP instance, used to open channels.
     */
    private Connection connection;

    /**
     * The channel AMQP instance, used to interact with the broker.
     */
    private Channel channel;

    /**
     * True if the client is correctly connected to the AMQP server
     * (the broker), false otherwise.
     */
    private boolean connected;

    /**
     * True if the client is correctly consuming his queue, false otherwise.
     */
    private boolean listening;


    /**
     * The standard constructor sets the used host.
     * @param host The broker host.
     */
    GenericAmqpClient(final String host) {
        if (Hosts.isKnownHost(host)) {
            this.host = host;
        }
    }


    /**
     * Opens connection of the client with the broker. If overrode, super must
     * be called immediately.
     */
    @Override
    public void connect() {
        try {
            final ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(host);

            if (connected) {
                throw new InvalidClientStateException();
            }

            connection = factory.newConnection();
            channel = connection.createChannel();
            connected = true;
            LOGGER.info("Connected to the broker, with host {}", host);

        } catch (InvalidClientStateException | IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }


    @Override
    public final void disconnect() {
        try {
            if (!connected) {
                throw new InvalidClientStateException();
            }
            connection.close();
            connected = false;
            listening = false;

        } catch (InvalidClientStateException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public final void addCallback(final Callback callback) {
        try {
            if (!connected || listening) {
                throw new InvalidClientStateException();
            }
            callbacks.add(callback);

        } catch (InvalidClientStateException e) {
            e.printStackTrace();
        }
    }


    @Override
    public final void listen() {
        try {
            if (!connected || listening) {
                throw new InvalidClientStateException();
            }

            channel.basicConsume(personalQueue, true,
                    generateAmqpCallback(), consumerTag -> { });

            listening = true;
            LOGGER.info("Listening in personal queue");

        } catch (InvalidClientStateException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public final String getPersonalQueueName() {
        return personalQueue;
    }


    @Override
    public final boolean isConnected() {
        return connected;
    }


    @Override
    public final boolean isListening() {
        return listening;
    }


    /**
     * Getter used to access to the logger instance from subclasses.
     * @return The logger.
     */
    protected static Logger getLogger() {
        return LOGGER;
    }


    /**
     * Getter used to access the Channel instance from subclasses.
     * @return The channel instance.
     */
    protected final Channel getChannel() {
        return channel;
    }


    /**
     * Setter for the client personal queue from subclasses.
     * @param queue The new queue.
     */
    protected final void setPersonalQueue(final String queue) {
        this.personalQueue = queue;
    }


    /**
     * Generates an AMQP callback as specified by the AmqpClient library,
     * composing it with the given message-type-relative callbacks.
     * @return The generated AMQP callback.
     */
    private DeliverCallback generateAmqpCallback() {
        return (consumerTag, rawMsg) -> {
            final Gson gson = new Gson();

            final String stringMsg = new String(rawMsg.getBody(), StandardCharsets.UTF_8);
            final String typeHeader = rawMsg.getProperties().getHeaders().get("type").toString();

            final Class<? extends Message> messageClass = MessageTypes.getClassFromType(typeHeader);
            final Message message = gson.fromJson(stringMsg, messageClass);

            LOGGER.info("Received {} into personal queue", MessageTypes.getTypeFromMessage(message));

            callbacks.stream()
                    .filter(msgTypeCallback -> msgTypeCallback.getMessageType().equals(messageClass))
                    .forEach(msgTypeCallback -> msgTypeCallback.execute(message));
        };
    }


    /**
     * Sends a message in a topic. The method abstracts the RabbitMQ
     * exchange-queue mechanism. Given a message, it creates a custom header
     * that indicates the message type; then, it creates a dedicated fanout
     * exchange, if not present. Finally, it sends the message in it.
     * @param topicName The name of topic.
     * @param message   The message to be sent, as an instance of the message
     *                  interface.
     */
    void sendMessageInTopic(final String topicName, final Message message) {
        try {
            final AMQP.BasicProperties customProps = generateCustomHeaderType(message);
            final byte[] serializedMsg = serializeMessage(message);

            channel.exchangeDeclare(topicName, "fanout");
            channel.basicPublish(topicName, "", customProps, serializedMsg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Sends a message in a queue, abstracting from RabbitMQ exchange-queue
     * mechanism. Given a message, it creates a custom header that indicates
     * the message type; declares the queue if not present, and sends the
     * message to the standard exchange (with the queue as the routing key).
     * @param queueName The name of the queue.
     * @param message   The message to be sent, as an instance of the message
     *                  interface.
     */
    void sendMessageInQueue(final String queueName, final Message message) {
        try {
            final AMQP.BasicProperties customProps = generateCustomHeaderType(message);
            final byte[] serializedMsg = serializeMessage(message);
            channel.basicPublish("", queueName, customProps, serializedMsg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * It creates a custom header for the AMQP message, including the
     * message type, inferring it from the message.
     * @param message The message, as a Message instance.
     * @return The custom properties of the message.
     */
    private AMQP.BasicProperties generateCustomHeaderType(final Message message) {
        String msgType = MessageTypes.getTypeFromMessage(message);

        Map<String, Object> headers = new HashMap<>();
        headers.put("type", msgType);

        return new AMQP.BasicProperties.Builder().headers(headers).build();
    }


    /**
     * It transforms the message in an array of of bytes, to be send
     * to the broker.
     * @param message The message, as a Message instance.
     * @return The message, as an array of bytes.
     */
    private byte[] serializeMessage(final Message message) {
        final Gson gson = new Gson();
        return gson.toJson(message).getBytes();
    }
}
