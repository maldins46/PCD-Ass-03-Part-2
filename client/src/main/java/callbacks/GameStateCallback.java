package callbacks;

import common.client.GameClient;
import common.client.config.Destinations;
import common.client.messages.GameStateMsg;
import common.client.messages.Message;
import common.gameState.ReplaceableGameState;
import common.model.Tile;
import gui.PuzzleGui;

import java.util.Comparator;

final class GameStateCallback extends GenericClientCallback {

    GameStateCallback(GameClient client, ReplaceableGameState gameState, PuzzleGui gui) {
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
    public void executeBody(final Message rawMessage) {
        final GameStateMsg message = (GameStateMsg) rawMessage;
        getGameState().updateData(message);
        getGui().rearrangeTiles();
    }
}
