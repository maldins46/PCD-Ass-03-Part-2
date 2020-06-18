package callbacks;

import client.GameClient;
import client.messages.Message;
import client.messages.RematchMsg;
import model.GameData;

public final class RematchCallback extends GenericServerCallback {
    public RematchCallback(final GameClient client, final GameData data) {
        super(client, data);
    }

    @Override
    public Class<? extends Message> getMessageType() {
        return RematchMsg.class;
    }

    @Override
    public void execute(final Message rawMessage) {
        final RematchMsg message = (RematchMsg) rawMessage;
        data.rematch();
        terminate(message);
    }
}
