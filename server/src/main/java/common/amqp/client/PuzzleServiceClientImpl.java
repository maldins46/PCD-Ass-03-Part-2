package common.amqp.client;

import common.amqp.config.Destinations;
import common.amqp.config.MessageTypes;
import common.amqp.messages.PuzzleServiceMsg;
import common.model.Player;

import java.io.IOException;

final class PuzzleServiceClientImpl extends GenericAmqpClient implements PuzzleServiceClient {

    /**
     * The standard constructor. It configures the host.
     * @param host The broker host.
     */
    PuzzleServiceClientImpl(final String host) {
        super(host);
    }

    @Override
    public void connect() {
        try {
            super.connect();
            getChannel().queueDeclare(Destinations.PUZZLE_SERVICE_QUEUE_NAME,
                    false, false, false, null).getQueue();
            setPersonalQueue(Destinations.PUZZLE_SERVICE_QUEUE_NAME);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendMessageToPlayer(final Player player, final PuzzleServiceMsg message) {
        try {
            if (!isConnected()) {
                throw new InvalidClientStateException();
            }

            sendMessageInQueue(player.getName(), message);
            getLogger().info("Sent message {} to player {}",
                    MessageTypes.getTypeFromMessage(message), player.getName());

        } catch (InvalidClientStateException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendMessageToMatch(PuzzleServiceMsg message) {
        try {
            if (!isConnected()) {
                throw new InvalidClientStateException();
            }

            sendMessageInTopic(Destinations.MATCH_TOPIC_NAME, message);
            getLogger().info("Sent message {} to the match topic",
                    MessageTypes.getTypeFromMessage(message));
        } catch (InvalidClientStateException e) {
            e.printStackTrace();
        }
    }
}
