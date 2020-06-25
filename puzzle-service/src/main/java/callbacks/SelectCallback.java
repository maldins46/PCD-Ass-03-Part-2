package callbacks;

import common.amqp.client.PuzzleServiceClient;
import common.amqp.messages.PlayerMsg;
import common.amqp.messages.SelectMsg;
import common.gameState.PuzzleServiceGameState;
import common.model.Player;
import common.model.Tile;

/**
 * The callback used when a new player notifies the will to select a tile.
 */
final class SelectCallback extends GenericPuzzleServiceCallback {

    /**
     * Standard callback, with superclass initialization.
     * @param client the game model.client.
     * @param gameState the game data.
     */
    SelectCallback(final PuzzleServiceClient client, final PuzzleServiceGameState gameState) {
        super(client, gameState);
    }


    @Override
    public Class<? extends PlayerMsg> getMessageType() {
        return SelectMsg.class;
    }


    @Override
    public void executeBody(final PlayerMsg rawMessage) {
        final SelectMsg message = (SelectMsg) rawMessage;
        final Tile tile = message.getSelectedTile();
        final Player selector = message.getSelector();
        getGameState().setTileAsSelected(tile, selector);
    }
}
