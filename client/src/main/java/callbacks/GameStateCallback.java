package callbacks;

import common.amqp.client.PlayerClient;
import common.amqp.messages.GameStateMsg;
import common.amqp.messages.Message;
import common.gameState.PlayerGameState;
import gui.GameGui;

final class GameStateCallback extends GenericClientCallback {

    GameStateCallback(final PlayerClient client,
            final PlayerGameState gameState,
                      final GameGui gui) {
        super(client, gameState, gui);
    }

    @Override
    public Class<? extends Message> getMessageType() {
        return GameStateMsg.class;
    }


    @Override
    public void executeBody(final Message rawMessage) {
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
