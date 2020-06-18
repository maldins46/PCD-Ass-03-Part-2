package callbacks;

import client.GameClient;
import client.messages.Message;
import client.messages.SelectMsg;
import model.GameData;
import model.Tile;

public final class SelectCallback extends GenericServerCallback {
    public SelectCallback(final GameClient client, final GameData data) {
        super(client, data);
    }

    @Override
    public Class<? extends Message> getMessageType() {
        return SelectMsg.class;
    }

    @Override
    public void execute(final Message rawMessage) {
        final SelectMsg message = (SelectMsg) rawMessage;
        final Tile tile = message.getSelectedTile();
        data.setTileAsSelected(tile.getOriginalPosition(), tile.getCurrentPosition(), tile.getSelectorPlayer());
        sendResponses(message);
    }
}
