package callbacks;

import common.client.CtxCallback;
import common.client.GameClient;
import common.gameState.ModifiableGameState;

/**
 * Factory that returns the callbacks used inside the server.
 */
public interface Callbacks {

    /**
     * Create a callback for the newPlayerMsg.
     * @param client The receiver of the callback.
     * @param state The entire puzzle as is.
     * @return the callback.
     */
    static CtxCallback newPlayerMsg(final GameClient client, final ModifiableGameState state) {
        return new NewPlayerCallback(client, state);
    }


    /**
     * Create a callback from a selectMsg callback.
     * @param client The receiver of the callback.
     * @param state The entire puzzle as is.
     * @return the callback.
     */
    static CtxCallback selectMsg(final GameClient client, final ModifiableGameState state) {
        return new SelectCallback(client, state);
    }


    /**
     * Create a callback from a swapRequestMsg callback.
     * @param client The receiver of the callback.
     * @param state The entire puzzle as is.
     * @return the callback.
     */
    static CtxCallback swapRequestMsg(final GameClient client, final ModifiableGameState state) {
        return new SwapCallback(client, state);
    }


    /**
     * Create a callback from a rematchMsg callback.
     * @param client The receiver of the callback.
     * @param state The entire puzzle as is.
     * @return the callback.
     */
    static CtxCallback rematchMsg(final GameClient client, final ModifiableGameState state) {
        return new RematchCallback(client, state);
    }
}
