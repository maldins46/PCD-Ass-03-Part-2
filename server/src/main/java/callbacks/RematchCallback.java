package callbacks;


import common.amqp.client.PuzzleServiceClient;
import common.amqp.messages.Message;
import common.amqp.messages.PlayerMsg;
import common.amqp.messages.RematchMsg;
import common.gameState.PuzzleServiceGameState;

/**
 * The callback used when a new player notifies the will to restart the game,
 * mixing the puzzle.
 */
final class RematchCallback extends GenericPuzzleServiceCallback {

    /**
     * Standard callback, with superclass initialization.
     * @param client the game model.client.
     * @param state the game data.
     */
    RematchCallback(final PuzzleServiceClient client, final PuzzleServiceGameState state) {
        super(client, state);
    }


    @Override
    public Class<? extends PlayerMsg> getMessageType() {
        return RematchMsg.class;
    }


    @Override
    public void executeBody(final Message rawMessage) {
        getState().rematch();
    }
}
