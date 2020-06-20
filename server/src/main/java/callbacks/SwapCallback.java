package callbacks;

import client.GameClient;
import client.messages.Message;
import client.messages.SwapMsg;
import model.GameData;
import model.Tile;

/**
 * The callback used when a new player notifies the will to swap two tiles.
 */
public final class SwapCallback extends GenericServerCallback {

    /**
     * Standard callback, with superclass initialization.
     * @param client the game client.
     * @param data the game data.
     */
    SwapCallback(final GameClient client, final GameData data) {
        super(client, data);
    }

    @Override
    public Class<? extends Message> getMessageType() {
        return SwapMsg.class;
    }

    @Override
    public void executeBody(final Message rawMessage) {
        final SwapMsg message = (SwapMsg) rawMessage;
        final Tile firstTile = message.getFirstTile();
        final Tile secondTile = message.getSecondTile();
        getData().swapTile(firstTile.getOriginalPosition(), firstTile.getCurrentPosition(),
                secondTile.getOriginalPosition(), secondTile.getCurrentPosition(),
                message.getFirstTile().getSelectorPlayer());
    }
}
