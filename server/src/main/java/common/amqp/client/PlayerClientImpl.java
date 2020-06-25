package common.amqp.client;

import common.amqp.callback.PlayerCallback;
import common.amqp.config.Destinations;
import common.amqp.config.MessageTypes;
import common.amqp.messages.PlayerMsg;
import common.model.Player;

import java.io.IOException;

/**
 * Implementation of the player AMQP client.
 */
final class PlayerClientImpl extends GenericAmqpClient implements PlayerClient {

    /**
     * The standard constructor. It configures the host.
     * @param host The broker host.
     */
    PlayerClientImpl(final String host) {
        super(host);
    }


    @Override
    public void connect() {
        try {
            super.connect();
            final String playerQueue = getChannel().queueDeclare().getQueue();
            getChannel().exchangeDeclare(Destinations.MATCH_TOPIC_NAME, "fanout");
            getChannel().queueBind(playerQueue, Destinations.MATCH_TOPIC_NAME, "");
            setPersonalQueue(playerQueue);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addCallback(final PlayerCallback callback) {
        super.addCallback(callback);
    }


    @Override
    public void sendMessageToPuzzleService(final PlayerMsg message) {
        try {
            if (!isConnected()) {
                throw new InvalidClientStateException();
            }

            message.setSenderPlayer(Player.of(getPersonalQueueName()));
            sendMessageInQueue(Destinations.PUZZLE_SERVICE_QUEUE_NAME, message);
            getLogger().info("Sent message {} to the puzzle service",
                    MessageTypes.getTypeFromMessage(message));

        } catch (InvalidClientStateException e) {
            e.printStackTrace();
        }
    }
}
