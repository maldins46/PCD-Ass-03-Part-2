package callbacks;

import common.amqp.client.PlayerClient;
import common.amqp.messages.AckMsg;
import common.amqp.messages.Message;
import common.amqp.messages.PlayerMsg;
import common.amqp.messages.PuzzleServiceMsg;
import common.gameState.PlayerGameState;
import gui.PlayerGui;

/**
 * A callback that react to an ack message from the puzzle service. The ack
 * unlocks the GUI, making the user continue the game.
 */
final class AckCallback extends GenericPlayerCallback {

    /**
     * Default constructor with field population in the superclass.
     * @param client The client instance.
     * @param gameState The game state instance.
     * @param gui The GUI accessor instance.
     */
    AckCallback(final PlayerClient client, final PlayerGameState gameState, final PlayerGui gui) {
        super(client, gameState, gui);
    }


    @Override
    public Class<? extends PuzzleServiceMsg> getMessageType() {
        return AckMsg.class;
    }


    @Override
    public void executeBody(final PuzzleServiceMsg rawMessage) {
        getGui().unlockPuzzle();
    }
}
