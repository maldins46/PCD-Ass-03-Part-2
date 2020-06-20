package callbacks;

import common.client.CtxCallback;
import common.client.GameClient;
import common.gameState.ModifiableGameState;
import common.gameState.ReadableGameState;
import common.gameState.ReplaceableGameState;
import gui.PuzzleGui;

public final class Callbacks {
    private Callbacks() { }

    /**
     * todo
     */
    public static CtxCallback gameStateMsg(final GameClient client, final ReplaceableGameState state, final PuzzleGui gui) {
        return new GameStateCallback(client, state, gui);
    }


    /**
     * todo
     */
    public static CtxCallback ackMsg(final GameClient client, final ReplaceableGameState state, final PuzzleGui gui) {
        return new AckCallback(client, state, gui);
    }


}
