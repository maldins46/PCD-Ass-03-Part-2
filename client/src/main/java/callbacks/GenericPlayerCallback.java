package callbacks;

import common.amqp.client.PlayerClient;
import common.amqp.config.MessageTypes;
import common.amqp.messages.Message;
import common.amqp.callback.PlayerCallback;
import common.gameState.PlayerGameState;

import gui.PlayerGui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a generic callback for the player module. This type of callbacks
 * have in common the client, state, and gui resource, and some code
 * for logging.
 */
public abstract class GenericPlayerCallback implements PlayerCallback {

    /**
     * The client instance is used principally to send message in response
     * of a request. In the player module, it is mainly unused.
     */
    private final PlayerClient client;

    /**
     * The game state instance is used to access the state of the game, and
     * take decisions (mainly linked to the GUI) depending on it.
     */
    private final PlayerGameState gameState;

    /**
     * The GUI instance is has methods to modify what it is shown to the user.
     */
    private final PlayerGui gui;

    /**
     * The logger gives some facility for debug purposes.
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(GenericPlayerCallback.class);


    /**
     * Initializes resouces used by the callbacks.
     * @param client The client resource.
     * @param gameState The game state resource.
     * @param gui The GUI accessor resource.
     */
    public GenericPlayerCallback(final PlayerClient client, final PlayerGameState gameState, final PlayerGui gui) {
        this.client = client;
        this.gameState = gameState;
        this.gui = gui;
    }


    @Override
    public final void execute(final Message rawMessage) {
        LOGGER.info("Received {} from puzzle service", MessageTypes.getTypeFromMessage(rawMessage));
        executeBody(rawMessage);
    }


    /**
     * This is the core part of the callback, different for every received
     * message.
     * @param rawMessage The received message.
     */
    protected abstract void executeBody(Message rawMessage);


    /**
     * Getter used to access the amqp client resource from subclasses.
     * @return The client instance.
     */
    protected final PlayerClient getClient() {
        return client;
    }


    /**
     * Getter used to access the game state resource from subclasses.
     * @return The game state instance.
     */
    protected final PlayerGameState getGameState() {
        return gameState;
    }


    /**
     * Getter used to access the GUI resource from subclasses.
     * @return The GUI instance.
     */
    protected final PlayerGui getGui() {
        return gui;
    }
}
