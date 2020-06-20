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
    private final GameClient client;

    /**
     * The game data.
     */
    private final GameData data;


    /**
     * Construct a generic callback with server and data.
     * @param client The game server.
     * @param data The game data.
     */
    public GenericServerCallback(final GameClient client, final GameData data) {
        this.client = client;
        this.data = data;
    }


    @Override
    public final String getDestination() {
        return Destinations.SERVER_QUEUE_NAME;
    }


    @Override
    public final void execute(final Message rawMessage) {
        executeBody(rawMessage);
        terminate(rawMessage);
    }


    private void terminate(final Message message) {
        PlayerTimers.getInstance().addOrUpdateTimer(message.getSender());

        client.sendMessage(data.generateGameDataMsg(), Destinations.MATCH_TOPIC_NAME);

        final AckMsg ack = new AckMsg(Destinations.SERVER_QUEUE_NAME, message.getSender());
        client.sendMessage(ack, message.getSender());
    }


    protected abstract void executeBody(Message rawMessage);


    /**
     * Getter for the game client instance, for child classes.
     * @return The game client.
     */
    protected GameClient getClient() {
        return client;
    }


    /**
     * Getter for the game data instance, for child classes.
     * @return The game data.
     */
    protected GameData getData() {
        return data;
    }
}
