package callbacks;

import common.amqp.CtxCallback;
import common.amqp.client.PlayerClient;
import common.gameState.PlayerGameState;
import gui.GameGui;

/**
 * Factory for callbacks.
 */
public final class  Callbacks {


    private Callbacks() { }

    /**
     * Create a GameState Callback. This callback handle a Msg that define the
     * updated structure of puzzle.
     * @param client The client that have to receive this message.
     * @param state The game state for client.
     * @param gui The graphic interface.
     * @return The GameStateCallbacks.
     */
    public static CtxCallback gameStateMsg(final PlayerClient client, final PlayerGameState state, final GameGui gui) {
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
    public static CtxCallback ackMsg(final PlayerClient client, final PlayerGameState state, final GameGui gui) {
        return new AckCallback(client, state, gui);
    }


}
