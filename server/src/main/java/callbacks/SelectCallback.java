package callbacks;

import client.GameClient;
import client.messages.Message;
import client.messages.SelectMsg;
import model.GameData;
import model.Tile;

/**
 * The callback used when a new player notifies the will to select a tile.
 */
public final class SelectCallback extends GenericServerCallback {

    /**
     * Standard callback, with superclass initialization.
     * @param client the game client.
     * @param data the game data.
     */
    SelectCallback(final GameClient client, final GameData data) {
        super(client, data);
    }

    @Override
    public Class<? extends Message> getMessageType() {
        return SelectMsg.class;
    }

    @Override
    public void executeBody(final Message rawMessage) {
        final SelectMsg message = (SelectMsg) rawMessage;
        final Tile tile = message.getSelectedTile();
        getData().setTileAsSelected(tile.getOriginalPosition(), tile.getCurrentPosition(), tile.getSelectorPlayer());
    }
}
