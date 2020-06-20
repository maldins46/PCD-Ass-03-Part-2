package callbacks;

import common.client.GameClient;
import common.client.messages.Message;
import common.client.messages.SelectMsg;
import common.gameState.ModifiableGameState;
import common.model.Tile;

/**
 * The callback used when a new player notifies the will to select a tile.
 */
final class SelectCallback extends GenericServerCallback {

    /**
     * Standard callback, with superclass initialization.
     * @param client the game model.client.
     * @param state the game data.
     */
    SelectCallback(final GameClient client, final ModifiableGameState state) {
        super(client, state);
    }

    @Override
    public Class<? extends Message> getMessageType() {
        return SelectMsg.class;
    }

    @Override
    public void executeBody(final Message rawMessage) {
        final SelectMsg message = (SelectMsg) rawMessage;
        final Tile tile = message.getSelectedTile();
        final String selector = message.getSelectorPlayer();
        getGameState().setTileAsSelected(tile, selector);
    }
}
