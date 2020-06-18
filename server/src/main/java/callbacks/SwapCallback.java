package callbacks;

import client.GameClient;
import client.messages.Message;
import client.messages.SwapMsg;
import model.GameData;
import model.Tile;

public final class SwapCallback extends GenericServerCallback {
    public SwapCallback(final GameClient client, final GameData data) {
        super(client, data);
    }

    @Override
    public Class<? extends Message> getMessageType() {
        return SwapMsg.class;
    }

    @Override
    public void execute(final Message rawMessage) {
        final SwapMsg message = (SwapMsg) rawMessage;
        final Tile firstTile = message.getFirstTile();
        final Tile secondTile = message.getSecondTile();
        data.swapTile(firstTile.getOriginalPosition(), firstTile.getCurrentPosition(),
                secondTile.getOriginalPosition(), secondTile.getCurrentPosition(),
                message.getFirstTile().getSelectorPlayer());
        terminate(message);
    }
}
