package callbacks;

import client.CtxCallback;
import client.GameClient;
import client.config.Destinations;
import client.messages.Message;
import client.messages.AckMsg;
import model.GameData;
import playerTimers.PlayerTimers;

/**
 * The class is a base for callback.
 */
public abstract class GenericServerCallback implements CtxCallback {

    /**
     * Server for the callback.
     */
    protected final GameClient client;

    /**
     * The game data.
     */
    protected final GameData data;


    /**
     * Construct a generic callback with server and data.
     * @param client The game' server.
     * @param data The game's data.
     */
    public GenericServerCallback(final GameClient client, final GameData data) {
        this.client = client;
        this.data = data;
    }

    @Override
    public final String getDestination() {
        return Destinations.SERVER_QUEUE_NAME;
    }


    protected final void terminate(final Message message) {
        PlayerTimers.getInstance().addOrUpdateTimer(message.getSender());

        client.sendMessage(data.generateGameDataMsg(), Destinations.MATCH_TOPIC_NAME);

        final AckMsg ack = new AckMsg(Destinations.SERVER_QUEUE_NAME, message.getSender());
        client.sendMessage(ack, message.getSender());

    }
}
