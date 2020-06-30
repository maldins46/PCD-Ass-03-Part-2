package callbacks;

import common.amqp.client.PlayerClient;
import common.amqp.messages.GameStateMsg;
import common.amqp.messages.PuzzleServiceMsg;
import common.gameState.PlayerGameState;
import gui.PlayerGui;

/**
 * A callback that reacts to GameState messages. When this message is received,
 * the client has to update its data structure, and update the puzzle displayed
 * inside the GUI accordingly.
 */
final class GameStateCallback extends GenericPlayerCallback {

    /**
     * Default constructor with field population in the superclass.
     * @param client The client instance.
     * @param gameState The game state instance.
     * @param gui The GUI accessor instance.
     */
    GameStateCallback(final PlayerClient client, final PlayerGameState gameState, final PlayerGui gui) {
        super(client, gameState, gui);
    }


    @Override
    public Class<? extends PuzzleServiceMsg> getMessageType() {
        return GameStateMsg.class;
    }


    @Override
    public void executeBody(final PuzzleServiceMsg rawMessage) {
        final GameStateMsg message = (GameStateMsg) rawMessage;
        getGameState().updateData(message);

        if (!getGameState().isGameJoined()) {
            getGui().leaveGame();

        } else if (getGameState().isFinished()) {
            getGui().finishMatch();

        } else if (!getGameState().isPlayerSelector()) {
            getGui().setPuzzleSelectable();

        } else if (getGameState().isPlayerSelector()) {
            getGui().setPuzzleSwappable();
        }
    }
}
