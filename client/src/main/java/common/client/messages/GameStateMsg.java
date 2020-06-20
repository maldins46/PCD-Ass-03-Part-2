package common.client.messages;

import common.gameState.GameState;

public final class GameStateMsg extends GenericMsg {
    private GameState gameState;

    public GameStateMsg(final String sender, final GameState gameState) {
        super(sender);
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }
}
