package callbacks;

import common.amqp.client.PlayerClient;
import common.amqp.messages.AckMsg;
import common.amqp.messages.Message;
import common.amqp.messages.PuzzleServiceMsg;
import common.gameState.PlayerGameState;
import gui.PlayerGui;

/**
 * A callback that react to an ack message from the puzzle service. The ack
 * unlocks the GUI, making the user continue the game.
 */
final class AckCallback extends GenericPlayerCallback {

    AckCallback(final PlayerClient client, final PlayerGameState gameState, final PlayerGui gui) {
        super(client, gameState, gui);
    }


    @Override
    public Class<? extends PuzzleServiceMsg> getMessageType() {
        return AckMsg.class;
    }


    @Override
    public void executeBody(final Message rawMessage) {
        getGui().unlockPuzzle();
    }
}
