package callbacks;

import common.client.GameClient;
import common.client.config.Destinations;
import common.client.messages.Message;
import common.client.messages.NewPlayerMsg;
import common.model.GameData;

/**
 * The callback used when a new player notifies the will to join the game.
 */
public final class NewPlayerCallback extends GenericServerCallback {

    /**
     * Standard callback, with superclass initialization.
     * @param client the game model.client.
     * @param data the game data.
     */
    NewPlayerCallback(final GameClient client, final GameData data) {
        super(client, data);
    }


    @Override
    public Class<? extends Message> getMessageType() {
        return NewPlayerMsg.class;
    }


    @Override
    public void executeBody(final Message rawMessage) {
        final NewPlayerMsg message = (NewPlayerMsg) rawMessage;
        getData().addPlayer(message.getSender());
        Destinations.addPlayerQueue(message.getSender());
        getClient().sendMessage(getData().generateGameDataMsg(), Destinations.MATCH_TOPIC_NAME);
    }
}
