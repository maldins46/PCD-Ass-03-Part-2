package callbacks;

import common.client.GameClient;
import common.client.config.Destinations;
import common.client.messages.AckMsg;
import common.client.messages.GameStateMsg;
import common.client.messages.Message;
import common.gameState.ReplaceableGameState;
import gui.PuzzleGui;

public final class AckCallback extends GenericClientCallback {
    public AckCallback(GameClient client, ReplaceableGameState gameState, PuzzleGui gui) {
        super(client, gameState, gui);
    }

    @Override
    public Class<? extends Message> getMessageType() {
        return AckMsg.class;
    }

    @Override
    public String getDestination() {
        return Destinations.CURRENT_TOPIC_QUEUE;
    }

    @Override
    public void execute(Message rawMessage) {
        getGui().unlockInterface();
    }
}
