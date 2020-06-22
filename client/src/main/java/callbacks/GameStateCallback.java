package callbacks;

import common.client.GameClient;
import common.client.config.Destinations;
import common.client.messages.GameStateMsg;
import common.client.messages.Message;
import common.client.messages.NewPlayerMsg;
import common.gameState.ReplaceableGameState;
import common.model.Tile;
import gui.PuzzleGui;

import java.util.Collections;
import java.util.Comparator;

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
    public void executeBody(final Message rawMessage) {
        final GameStateMsg message = (GameStateMsg) rawMessage;
        getGameState().updateData(message);
        getGameState().getPuzzle().getTiles()
                .sort(Comparator.comparingInt(Tile::getOriginalPosition));

        getGui().rearrangeTiles();
    }
}
