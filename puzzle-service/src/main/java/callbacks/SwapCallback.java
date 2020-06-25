package callbacks;

import common.amqp.client.PuzzleServiceClient;
import common.amqp.messages.PlayerMsg;
import common.amqp.messages.SwapMsg;
import common.gameState.PuzzleServiceGameState;
import common.model.Player;
import common.model.Tile;

/**
 * The callback used when a new player notifies the will to swap two tiles.
 */
final class SwapCallback extends GenericPuzzleServiceCallback {

    /**
     * Standard callback, with superclass initialization.
     * @param client the game model.client.
     * @param gameState the game data.
     */
    SwapCallback(final PuzzleServiceClient client, final PuzzleServiceGameState gameState) {
        super(client, gameState);
    }


    @Override
    public Class<? extends PlayerMsg> getMessageType() {
        return SwapMsg.class;
    }


    @Override
    public void executeBody(final PlayerMsg rawMessage) {
        final SwapMsg message = (SwapMsg) rawMessage;
        final Tile startTile = message.getStartTile();
        final Tile destTile = message.getDestTile();
        final Player selector = message.getSelector();
        getGameState().swapTiles(startTile, destTile, selector);
    }
}
