package callbacks;

import common.client.GameClient;
import common.client.config.Destinations;
import common.client.messages.GameStateMsg;
import common.client.messages.Message;
import common.client.messages.NewPlayerMsg;
import common.gameState.ReplaceableGameState;
import gui.PuzzleGui;

public final class GameStateCallback extends GenericClientCallback {
    public GameStateCallback(GameClient client, ReplaceableGameState gameState, PuzzleGui gui) {
        super(client, gameState, gui);
    }

    @Override
    public Class<? extends Message> getMessageType() {
        return GameStateMsg.class;
    }

    @Override
    public String getDestination() {
        return Destinations.MATCH_TOPIC_NAME;
    }

    @Override
    public void execute(Message rawMessage) {
        final GameStateMsg message = (GameStateMsg) rawMessage;
        getGui().rearrangeTiles(message.getGameState());
    }
}
