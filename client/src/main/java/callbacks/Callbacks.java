package callbacks;

import common.client.CtxCallback;
import common.client.GameClient;
import common.gameState.ReplaceableGameState;
import gui.PuzzleGui;

/**
 * Factory for callbacks.
 */
public interface Callbacks {

    /**
     * Create a GameState Callback. This callback handle a Msg that define the
     * updated structure of puzzle.
     * @param client The client that have to receive this message.
     * @param state The game state for client.
     * @param gui The graphic interface.
     * @return The GameStateCallbacks.
     */
    static CtxCallback gameStateMsg(final GameClient client, final ReplaceableGameState state, final PuzzleGui gui) {
        return new GameStateCallback(client, state, gui);
    }


    /**
     * Create a AckMsg Callback. This callback handles AckMsg.
     * This msg confirms an action of the player.
     * @param client The client that have to receive this message.
     * @param state The game state for client.
     * @param gui The graphic interface.
     * @return The AckCallback.
     */
    static CtxCallback ackMsg(final GameClient client, final ReplaceableGameState state, final PuzzleGui gui) {
        return new AckCallback(client, state, gui);
    }


}
