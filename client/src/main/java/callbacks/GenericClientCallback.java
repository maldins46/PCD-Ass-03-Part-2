package callbacks;

import common.client.CtxCallback;
import common.client.GameClient;
import common.client.config.MessageTypes;
import common.client.messages.Message;
import common.gameState.ClientGameState;

import gui.PuzzleGui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GenericClientCallback implements CtxCallback {

    /**
     * The client that have this callback.
     */
    private final GameClient client;

    /**
     * The GameState of the client.
     */
    private final ClientGameState gameState;

    /**
     * The graphic interface.
     */
    private final PuzzleGui gui;

    /**
     * The logger.
     */
    protected static final Logger logger = LoggerFactory.getLogger(GenericClientCallback.class);


    /**
     * Create a generic client callback.
     * @param client The client that want to receive the callback.
     * @param gameState The game state of game.
     * @param gui The graphic interface.
     */
    public GenericClientCallback(final GameClient client,
                                 final ClientGameState gameState,
                                 final PuzzleGui gui) {
        this.client = client;
        this.gameState = gameState;
        this.gui = gui;
    }


    @Override
    public final void execute(final Message rawMessage) {
        logger.info("Received " + MessageTypes.getTypeFromMessage(rawMessage) + " from " + rawMessage.getSender());
        executeBody(rawMessage);
    }


    /**
     * This is the core part of the callback, different for each received
     * message. Do not include the termination part in it.
     * @param rawMessage The received message.
     */
    protected abstract void executeBody(Message rawMessage);

    /**
     * Return the host.
     * @return The host.
     */
    protected final GameClient getClient() {
        return client;
    }

    /**
     * Getter for the instance of client associated.
     * @return The client's instance.
     */
    protected final ClientGameState getGameState() {
        return gameState;
    }

    /**
     * Getter for the graphic interface.
     * @return The graphic interface.
     */
    protected final PuzzleGui getGui() {
        return gui;
    }
}
