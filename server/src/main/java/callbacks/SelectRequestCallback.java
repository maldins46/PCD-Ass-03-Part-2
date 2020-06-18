package callbacks;

import client.GameClient;
import client.messages.Message;
import client.messages.SelectRequestMsg;
import model.GameData;
import model.Tile;

public final class SelectRequestCallback extends GenericServerCallback {
    public SelectRequestCallback(final GameClient client, final GameData data) {
        super(client, data);
    }

    @Override
    public Class<? extends Message> getMessageType() {
        return SelectRequestMsg.class;
    }

    @Override
    public void execute(final Message rawMessage) {
        final SelectRequestMsg message = (SelectRequestMsg) rawMessage;
        final Tile tile = message.getSelectedTile();
        data.setTileAsSelected(tile.getOriginalPosition(), tile.getCurrentPosition(), tile.getSelectorPlayer());
        sendResponses(message);
    }
}
