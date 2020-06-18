package callbacks;

import client.CtxCallback;
import client.GameClient;
import client.config.Destinations;
import client.messages.Message;
import client.messages.RequestAckMsg;
import model.GameData;

public abstract class GenericServerCallback implements CtxCallback {
    protected final GameClient client;
    protected final GameData data;


    public GenericServerCallback(final GameClient client, final GameData data) {
        this.client = client;
        this.data = data;
    }

    @Override
    public String getDestination() {
        return Destinations.SERVER_QUEUE_NAME;
    }

    protected void sendResponses(final Message message) {
        client.sendMessage(data.generateGameDataMsg(), Destinations.MATCH_TOPIC_NAME);

        final RequestAckMsg ack = new RequestAckMsg(Destinations.SERVER_QUEUE_NAME, message.getSender());
        client.sendMessage(ack, message.getSender());
    }
}
