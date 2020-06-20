package callbacks;

import common.client.CtxCallback;
import common.client.GameClient;
import common.gameState.ReplaceableGameState;
import gui.PuzzleGui;

public abstract class GenericClientCallback implements CtxCallback {
    private final GameClient client;

    private final ReplaceableGameState gameState;

    private final PuzzleGui gui;

    public GenericClientCallback(final GameClient client, final ReplaceableGameState gameState, final PuzzleGui gui) {
        this.client = client;
        this.gameState = gameState;
        this.gui = gui;
    }

    protected final GameClient getClient() {
        return client;
    }

    protected final ReplaceableGameState getGameState() {
        return gameState;
    }

    protected final PuzzleGui getGui() {
        return gui;
    }
}
