package callbacks;

import common.amqp.client.PlayerClient;
import common.amqp.messages.AckMsg;
import common.amqp.messages.Message;
import common.gameState.PlayerGameState;
import gui.GameGui;

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
    AckCallback(final PlayerClient client, final PlayerGameState gameState, final GameGui gui) {
        super(client, gameState, gui);
    }

    @Override
    public Class<? extends Message> getMessageType() {
        return AckMsg.class;
    }


    @Override
    public void executeBody(final Message rawMessage) {
        getGui().unlockPuzzle();
    }
}
