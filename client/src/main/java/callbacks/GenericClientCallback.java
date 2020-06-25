package callbacks;

import common.amqp.CtxCallback;
import common.amqp.client.PlayerClient;
import common.amqp.config.MessageTypes;
import common.amqp.messages.Message;
import common.gameState.PlayerGameState;

import gui.GameGui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GenericClientCallback implements CtxCallback {

    /**
     * The client that have this callback.
     */
    private final PlayerClient client;

    /**
     * The GameState of the client.
     */
    private final PlayerGameState gameState;

    /**
     * The graphic interface.
     */
    private final GameGui gui;

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
    public GenericClientCallback(final PlayerClient client,
                                 final PlayerGameState gameState,
                                 final GameGui gui) {
        this.client = client;
        this.gameState = gameState;
        this.gui = gui;
    }


    @Override
    public final void execute(final Message rawMessage) {
        logger.info("Received {} from puzzle service", MessageTypes.getTypeFromMessage(rawMessage));
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
    protected final PlayerClient getClient() {
        return client;
    }

    /**
     * Getter for the instance of client associated.
     * @return The client's instance.
     */
    protected final PlayerGameState getGameState() {
        return gameState;
    }

    /**
     * Getter for the graphic interface.
     * @return The graphic interface.
     */
    protected final GameGui getGui() {
        return gui;
    }
}
