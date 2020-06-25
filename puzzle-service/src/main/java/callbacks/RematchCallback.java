package callbacks;


import common.amqp.client.PuzzleServiceClient;
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
     * @param gameState the game data.
     */
    RematchCallback(final PuzzleServiceClient client, final PuzzleServiceGameState gameState) {
        super(client, gameState);
    }


    @Override
    public Class<? extends PlayerMsg> getMessageType() {
        return RematchMsg.class;
    }


    @Override
    public void executeBody(final PlayerMsg rawMessage) {
        getGameState().rematch();
    }
}
