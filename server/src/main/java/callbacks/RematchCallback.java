package callbacks;

import common.client.GameClient;
import common.client.messages.Message;
import common.client.messages.RematchMsg;
import common.gameState.ServerGameState;

/**
 * The callback used when a new player notifies the will to restart the game,
 * mixing the puzzle.
 */
final class RematchCallback extends GenericServerCallback {

    /**
     * Standard callback, with superclass initialization.
     * @param client the game model.client.
     * @param state the game data.
     */
    RematchCallback(final GameClient client, final ServerGameState state) {
        super(client, state);
    }

    @Override
    public Class<? extends Message> getMessageType() {
        return RematchMsg.class;
    }

    @Override
    public void executeBody(final Message rawMessage) {
        getGameState().rematch();
    }
}
