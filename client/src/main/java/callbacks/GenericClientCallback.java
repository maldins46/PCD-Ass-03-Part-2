package callbacks;

import common.client.CtxCallback;
import common.client.GameClient;
import common.client.config.Destinations;
import common.client.config.MessageTypes;
import common.client.messages.AckMsg;
import common.client.messages.Message;
import common.gameState.ReplaceableGameState;
import common.model.Player;
import gui.PuzzleGui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GenericClientCallback implements CtxCallback {
    private final GameClient client;

    private final ReplaceableGameState gameState;

    private final PuzzleGui gui;

    protected static final Logger logger = LoggerFactory.getLogger(GenericClientCallback.class);


    public GenericClientCallback(final GameClient client, final ReplaceableGameState gameState, final PuzzleGui gui) {
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


    protected final GameClient getClient() {
        return client;
    }

    protected final ReplaceableGameState getGameState() {
        return gameState;
    }

    protected final PuzzleGui getGui() {
        return gui;
    }
}
