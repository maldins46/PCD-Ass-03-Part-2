package callbacks;

import common.client.GameClient;
import common.client.config.Destinations;
import common.client.messages.AckMsg;
import common.client.messages.Message;
import common.gameState.ReplaceableGameState;
import gui.PuzzleGui;

/**
 * Create a callback for the AckMsg. This msg confirms an action of the player.
 */
public final class AckCallback extends GenericClientCallback {

    /**
     * Create an AckCallback.
     * @param client The client to respond with an Ack.
     * @param gameState The state of the game.
     * @param gui The graphic interface.
     */
    public AckCallback(final GameClient client,
                       final ReplaceableGameState gameState,
                       final PuzzleGui gui) {
        super(client, gameState, gui);
    }

    @Override
    public Class<? extends Message> getMessageType() {
        return AckMsg.class;
    }

    @Override
    public String getDestination() {
        return Destinations.MAIN_CLIENT_QUEUE;
    }

    @Override
    public void executeBody(final Message rawMessage) {
        getGui().unlockInterface();
    }
}
