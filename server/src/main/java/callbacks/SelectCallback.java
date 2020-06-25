package callbacks;

import common.amqp.client.PuzzleServiceClient;
import common.amqp.messages.Message;
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
     * @param state the game data.
     */
    SelectCallback(final PuzzleServiceClient client, final PuzzleServiceGameState state) {
        super(client, state);
    }

    @Override
    public Class<? extends PlayerMsg> getMessageType() {
        return SelectMsg.class;
    }

    @Override
    public void executeBody(final Message rawMessage) {
        final SelectMsg message = (SelectMsg) rawMessage;
        final Tile tile = message.getSelectedTile();
        final Player selector = message.getSelector();
        getState().setTileAsSelected(tile, selector);
    }
}
