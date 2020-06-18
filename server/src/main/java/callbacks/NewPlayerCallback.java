package callbacks;

import client.GameClient;
import client.config.Destinations;
import client.messages.Message;
import client.messages.NewPlayerMsg;
import model.GameData;

public final class NewPlayerCallback extends GenericServerCallback {

    public NewPlayerCallback(final GameClient client, final GameData data) {
        super(client, data);
    }


    @Override
    public Class<? extends Message> getMessageType() {
        return NewPlayerMsg.class;
    }


    @Override
    public void execute(final Message rawMessage) {
        final NewPlayerMsg message = (NewPlayerMsg) rawMessage;
        data.addPlayer(message.getSender());
        Destinations.addPlayerQueue(message.getSender());
        client.sendMessage(data.generateGameDataMsg(), Destinations.MATCH_TOPIC_NAME);

        sendResponses(message);
    }
}
