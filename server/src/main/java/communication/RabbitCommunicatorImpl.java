package communication;

import com.google.gson.Gson;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.AMQP;

import communication.messages.Message;
import communication.messages.NewPlayerMsg;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public final class RabbitCommunicatorImpl implements RabbitCommunicator {

    private final Hosts host;
    private Connection connection;
    private Channel channel;

    private final Map<MessageTypes, Callback> serverQueueCallbacks;
    private final Map<MessageTypes, Callback> matchTopicCallbacks;


    RabbitCommunicatorImpl(final Hosts host, final Map<MessageTypes, Callback> serverQueueCallbacks, final Map<MessageTypes, Callback> matchTopicCallbacks) {
        this.host = host;
        this.serverQueueCallbacks = serverQueueCallbacks;
        this.matchTopicCallbacks = matchTopicCallbacks;
    }


    @Override
    public void connect() {
        final ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host.toString());

        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

        // fixme due call?
        handlerCallback(serverQueueCallbacks, Destinations.SERVER_QUEUE_NAME);
        handlerCallback(matchTopicCallbacks, Destinations.MATCH_TOPIC_NAME);
    }

    private void handlerCallback(final Map<MessageTypes, Callback> callbacks, final Destinations destinations){
        if (callbacks.size() > 0) {
            final DeliverCallback serverQueueCallback = (consumerTag, rawMsg) -> {
                final Gson gson = new Gson();
                final String stringifiedMsg = new String(rawMsg.getBody(), StandardCharsets.UTF_8);
                final MessageTypes msgType = MessageTypes.valueOfString((String) rawMsg.getProperties().getHeaders().get("type"));

                switch (msgType) {
                    case NEW_PLAYER_MSG:
                        NewPlayerMsg newPlayerMsg = gson.fromJson(stringifiedMsg, NewPlayerMsg.class);
                        callbacks.get(msgType).execute(newPlayerMsg);
                        break;


                    default:
                        break;
                }
            };

            try {
                channel.queueDeclare(destinations.toString(), false, false, false, null);
                if (destinations.equals(Destinations.MATCH_TOPIC_NAME)){
                    final String queueName = channel.queueDeclare().getQueue();
                    channel.queueBind(queueName, destinations.toString(), "");
                }
                channel.basicConsume(destinations.toString(), true, serverQueueCallback, consumerTag -> { });
            } catch (IOException e) {
                e.printStackTrace();
            }
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
    public void sendMessage(final Message message, final Destinations destination) {
        MessageTypes msgType = MessageTypes.valueFromMessage(message);
        AMQP.BasicProperties headers = new AMQP.BasicProperties();
        headers.getHeaders().put("type", msgType.toString());

        Gson gson = new Gson();
        String rawMsg = gson.toJson(message);

        switch (destination) {
            case MATCH_TOPIC_NAME:
                try {
                    channel.exchangeDeclare(Destinations.MATCH_TOPIC_NAME.toString(), "fanout");
                    channel.basicPublish(Destinations.MATCH_TOPIC_NAME.toString(), "", headers, rawMsg.getBytes());

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case SERVER_QUEUE_NAME:
                try {
                channel.queueDeclare(Destinations.SERVER_QUEUE_NAME.toString(), false, false, false, null);
                channel.basicPublish("", Destinations.SERVER_QUEUE_NAME.toString(), headers, rawMsg.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            default:
                break;
        }
    }
}
