package callbacks;

import common.amqp.client.PuzzleServiceClient;
import common.amqp.messages.Message;
import common.amqp.messages.SwapMsg;
import common.gameState.PuzzleServiceGameState;
import common.model.Player;
import common.model.Tile;

/**
 * The callback used when a new player notifies the will to swap two tiles.
 */
final class SwapCallback extends GenericServerCallback {

    /**
     * Standard callback, with superclass initialization.
     * @param client the game model.client.
     * @param state the game data.
     */
    SwapCallback(final PuzzleServiceClient client, final PuzzleServiceGameState state) {
        super(client, state);
    }

    @Override
    public Class<? extends Message> getMessageType() {
        return SwapMsg.class;
    }

    @Override
    public void executeBody(final Message rawMessage) {
        final SwapMsg message = (SwapMsg) rawMessage;
        final Tile startTile = message.getStartTile();
        final Tile destTile = message.getDestTile();
        final Player selector = message.getSelector();
        getState().swapTiles(startTile, destTile, selector);
    }
}
