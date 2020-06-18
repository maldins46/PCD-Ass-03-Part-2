package communication;

import com.google.gson.Gson;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.AMQP;

import communication.messages.Message;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public final class RabbitCommunicatorImpl implements RabbitCommunicator {

    /**
     * todo
     */
    private final String host;

    /**
     * todo
     */
    private final Set<MicroCallback> microCallbacks;

    /**
     * todo
     */
    private Connection connection;

    /**
     * todo
     */
    private Channel channel;


    /**
     * todo
     */
    RabbitCommunicatorImpl(final String host, final Set<MicroCallback> microCallbacks) {
        this.host = host;
        this.microCallbacks = microCallbacks;
    }


    @Override
    public void connect() {
        final ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);

        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

        activateCallbackForDest(Destinations.SERVER_QUEUE_NAME);
        activateCallbackForDest(Destinations.MATCH_TOPIC_NAME);
    }


    /**
     * todo
     */
    private void activateCallbackForDest(final String destination) {
        final Set<MicroCallback> destinationMicroCallbacks = microCallbacks.stream()
                .filter(microCallback -> microCallback.getDestination().equals(destination))
                .collect(Collectors.toSet());

        if (!destinationMicroCallbacks.isEmpty()) {
            final DeliverCallback destCallback = (consumerTag, rawMsg) -> {
                final Gson gson = new Gson();

                final String stringifiedMsg = new String(rawMsg.getBody(), StandardCharsets.UTF_8);
                final String typeHeader = (String) rawMsg.getProperties().getHeaders().get("type");

                final Class<? extends Message> messageClass = MessageTypes.getClassFromType(typeHeader);
                final Message message = gson.fromJson(stringifiedMsg, messageClass);

                destinationMicroCallbacks.stream()
                        .filter(microCallback -> microCallback.getMessageType().equals(typeHeader))
                        .forEach(microCallback -> microCallback.execute(message));
            };

            createDestAndSetCallback(destination, destCallback);
        }
    }


    /**
     *
     * @param destination
     * @param destCallback
     */
    private void createDestAndSetCallback(String destination, DeliverCallback destCallback) {
        try {
            if (destination.equals(Destinations.MATCH_TOPIC_NAME)) {
                // create exchange and queue if not present (miming a subscribe mechanism)
                channel.queueDeclare(destination, false, false, false, null);
                final String queueName = channel.queueDeclare().getQueue();
                channel.queueBind(queueName, destination, "");

                // subscribe to the queue
                channel.basicConsume(queueName, true, destCallback, consumerTag -> {
                });

            } else if (destination.equals(Destinations.SERVER_QUEUE_NAME)) {
                // create a queue with a specified name, if not present
                channel.queueDeclare(destination, false, false, false, null);

                // subscribe to the queue
                channel.basicConsume(destination, true, destCallback, consumerTag -> {
                });
            }

        } catch (IOException e) {
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
    public void sendMessage(final Message message, final String destination) {
        // generates the appropriate header
        String msgType = MessageTypes.getTypeFromMessage(message);
        AMQP.BasicProperties msgProperties = new AMQP.BasicProperties();
        msgProperties.getHeaders().put("type", msgType);

        Gson gson = new Gson();
        String rawMsg = gson.toJson(message);

        if (destination.equals(Destinations.MATCH_TOPIC_NAME)) {
            try {
                // declares a custom exchange for the match, if not present,
                // and publishes a message in it
                channel.exchangeDeclare(Destinations.MATCH_TOPIC_NAME, "fanout");
                channel.basicPublish(Destinations.MATCH_TOPIC_NAME, "", msgProperties, rawMsg.getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (destination.equals(Destinations.SERVER_QUEUE_NAME)) {
            try {
                // declares the server queue, if not present, and publishes
                // a message in it
                channel.queueDeclare(Destinations.SERVER_QUEUE_NAME, false, false, false, null);
                channel.basicPublish("", Destinations.SERVER_QUEUE_NAME, msgProperties, rawMsg.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
