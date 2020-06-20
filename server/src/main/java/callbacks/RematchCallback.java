package callbacks;

import client.GameClient;
import client.messages.Message;
import client.messages.RematchMsg;
import model.GameData;

/**
 * The callback used when a new player notifies the will to restart the game,
 * mixing the puzzle.
 */
public final class RematchCallback extends GenericServerCallback {

    /**
     * Standard callback, with superclass initialization.
     * @param client the game client.
     * @param data the game data.
     */
    RematchCallback(final GameClient client, final GameData data) {
        super(client, data);
    }

    @Override
    public Class<? extends Message> getMessageType() {
        return RematchMsg.class;
    }

    @Override
    public void executeBody(final Message rawMessage) {
        getData().rematch();
    }
}
