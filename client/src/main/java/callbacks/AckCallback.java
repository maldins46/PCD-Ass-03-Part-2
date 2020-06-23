package callbacks;

import common.client.GameClient;
import common.client.messages.AckMsg;
import common.client.messages.Message;
import common.gameState.ClientGameState;
import gui.PuzzleGui;

/**
 * Create a callback for the AckMsg. This msg confirms an action of the player.
 */
final class AckCallback extends GenericClientCallback {

    /**
     * Create an AckCallback.
     * @param client The client to respond with an Ack.
     * @param gameState The state of the game.
     * @param gui The graphic interface.
     */
    AckCallback(final GameClient client,
                       final ClientGameState gameState,
                       final PuzzleGui gui) {
        super(client, gameState, gui);
    }

    @Override
    public Class<? extends Message> getMessageType() {
        return AckMsg.class;
    }


    @Override
    public void executeBody(final Message rawMessage) {
        getGui().unlockInterface();
    }
}
