package callbacks;

import common.client.CtxCallback;
import common.client.GameClient;
import common.client.config.Destinations;
import common.client.messages.Message;
import common.client.messages.AckMsg;
import common.gameState.ModifiableGameState;
import common.model.Player;
import timeouts.PlayerTimeouts;

/**
 * The class is a base for callback.
 */
abstract class GenericServerCallback implements CtxCallback {

    /**
     * Server for the callback.
     */
    private final GameClient client;

    /**
     * The game data.
     */
    private final ModifiableGameState gameState;


    /**
     * Construct a generic callback with server and data.
     * @param client The game server.
     * @param gameState The game data.
     */
    public GenericServerCallback(final GameClient client, final ModifiableGameState gameState) {
        this.client = client;
        this.gameState = gameState;
    }


    @Override
    public final String getDestination() {
        return Destinations.MAIN_CLIENT_QUEUE;
    }


    @Override
    public final void execute(final Message rawMessage) {
        executeBody(rawMessage);
        terminate(rawMessage);
    }


    /**
     * Every callback inside the server has a final part in model.common: it has to
     * update the player timer, send the update in the match topic and send
     * the ack to the player. This method handles it.
     * @param message The received message.
     */
    private void terminate(final Message message) {
        PlayerTimeouts.addOrUpdateTimer(Player.of(message.getSender()), client, gameState);

        client.sendMessage(gameState.generateGameDataMsg(), Destinations.MATCH_TOPIC_NAME);

        final AckMsg ack = new AckMsg(Destinations.SERVER_QUEUE_NAME, Player.of(message.getSender()));
        client.sendMessage(ack, message.getSender());
    }


    /**
     * This is the core part of the callback, different for each received
     * message. Do not include the termination part in it.
     * @param rawMessage The received message.
     */
    protected abstract void executeBody(Message rawMessage);


    /**
     * Getter for the game model.client instance, for child classes.
     * @return The game model.client.
     */
    protected GameClient getClient() {
        return client;
    }


    /**
     * Getter for the game data instance, for child classes.
     * @return The game data.
     */
    protected ModifiableGameState getGameState() {
        return gameState;
    }
}
