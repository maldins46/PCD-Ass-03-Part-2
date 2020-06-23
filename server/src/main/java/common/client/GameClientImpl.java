package common.client;

import com.google.gson.Gson;
import com.rabbitmq.client.*;
import common.client.config.Destinations;
import common.client.config.Hosts;
import common.client.config.MessageTypes;
import common.client.messages.Message;
import common.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

public final class GameClientImpl implements GameClient {

    private static final Logger logger = LoggerFactory.getLogger(GameClientImpl.class);

    /**
     * The broker host. "Localhost" is the standard used for tests, meanwhile
     * "rabbit" is the DNS name for the RabbitMQ container in the local server
     * network.
     */
    private String host = Hosts.LOCAL;

    /**
     * Standard callbacks, i.e. of which anyone is related to the server queue
     * or the match topic, and that is related to a specific message type.
     */
    private final Set<CtxCallback> ctxCallbacks = new HashSet<>();

    /**
     * The connection AMQP instance, used to open channels.
     */
    private Connection connection;

    /**
     * The channel AMQP instance, used to interact with the broker.
     */
    private Channel channel;

    /**
     * Check if is client or server.
     */
    private final boolean isServerModule;


    /**
     * The standard constructor, that configures host and callbacks.
     * @param host The broker host.
     */
    GameClientImpl(final String host, final boolean isServerModule) {
        if (Hosts.isKnownHost(host)) {
            this.host = host;
        }

        this.isServerModule = isServerModule;
    }


    @Override
    public void connect() {
        final ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);

        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            logger.info("Connected to the broker, with host " + host);

            if (isServerModule) {
                final String queueName = channel.queueDeclare(Destinations.SERVER_QUEUE_NAME,
                        false, false, false, null).getQueue();
                Destinations.setPersonalQueue(queueName);

            } else {
                final String queueName = channel.queueDeclare().getQueue();
                channel.exchangeDeclare(Destinations.MATCH_TOPIC_NAME, "fanout");
                channel.queueBind(queueName, Destinations.MATCH_TOPIC_NAME, "");
                Destinations.setPersonalQueue(queueName);
            }
            logger.info("Personal queue subscribed");

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void disconnect() {
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addCallback(final CtxCallback callback) {
        ctxCallbacks.add(callback);
    }


    @Override
    public void listen() {
        try {
            channel.basicConsume(Destinations.PERSONAL_QUEUE, true,
                    generateAmqpCallback(), consumerTag -> { });
            logger.info("Listening in personal queue");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessageToServer(final Message message) {
        sendMessageInQueue(Destinations.SERVER_QUEUE_NAME, message);
        logger.info("Sent message "+ MessageTypes.getTypeFromMessage(message) + " to the server");
    }

    @Override
    public void sendMessageToPlayer(final Player player, final Message message) {
        if (Destinations.isPlayerQueue(player.getName())) {
            sendMessageInQueue(player.getName(), message);
            logger.info("Sent message "+ MessageTypes.getTypeFromMessage(message) +" to player " + player.getName());
        }
    }


    @Override
    public void sendMessageToMatch(final Message message) {
        sendMessageInTopic(Destinations.MATCH_TOPIC_NAME, message);
        logger.info("Sent message "+ MessageTypes.getTypeFromMessage(message) +" to the match topic");
    }


    /**
     * Generates a callback as specified by the AmqpClient library, composing it
     * with the given stdCallbacks.
     * @return The generated callback.
     */
    private DeliverCallback generateAmqpCallback() {
        return (consumerTag, rawMsg) -> {
            final Gson gson = new Gson();

            final String stringifiedMsg = new String(rawMsg.getBody(), StandardCharsets.UTF_8);
            final String typeHeader = rawMsg.getProperties().getHeaders().get("type").toString();

            final Class<? extends Message> messageClass = MessageTypes.getClassFromType(typeHeader);
            final Message message = gson.fromJson(stringifiedMsg, messageClass);

            logger.info("Received " + MessageTypes.getTypeFromMessage(message) + " into personal queue");

            ctxCallbacks.stream()
                    .filter(ctxCallback -> ctxCallback.getMessageType().equals(messageClass))
                    .forEach(ctxCallback -> ctxCallback.execute(message));
        };
    }


    /**
     * Sends a message in a topic, that is something that can be subscribed
     * by at least one clients. The method abstracts the RabbitMQ
     * exchange-queue mechanism. Given a message, it creates a custom header
     * that indicates the message type; Then it creates a dedicated fanout
     * exchange, if not present. Then, it sends the message in it.
     * @param message The message to be sent, as an instance of the message
     *                interface.
     */
    private void sendMessageInTopic(final String topicName, final Message message) {
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
     * @param queueName The name of the topic.
     * @param message The message to be sent, as an instance of the message
     *                interface.
     */
    private void sendMessageInQueue(final String queueName, final Message message) {
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
        headers.put("type",  msgType);

        return new AMQP.BasicProperties.Builder().headers(headers).build();
    }


    /**
     * It trasforms the message in an array of of bytes, to be send
     * to the broker.
     * @param message The message, as a Message instance.
     * @return The message, as an array of bytes.
     */
    private byte[] serializeMessage(final Message message) {
        final Gson gson = new Gson();
        return gson.toJson(message).getBytes();
    }
}
