package callbacks;

import common.client.CtxCallback;
import common.client.GameClient;
import common.gameState.ModifiableGameState;

/**
 * Factory that returns the callbacks used inside the server.
 */
public final class Callbacks {

    private Callbacks() { }

    /**
     * Create a callback for the newPlayerMsg.
     * @param client The receiver of the callback.
     * @param data The entire puzzle as is.
     * @return the callback.
     */
    public static CtxCallback newPlayerMsg(final GameClient client, final ModifiableGameState data) {
        return new NewPlayerCallback(client, data);
    }


    /**
     * Create a callback from a selectMsg callback.
     * @param client The receiver of the callback.
     * @param data The entire puzzle as is.
     * @return the callback.
     */
    public static CtxCallback selectMsg(final GameClient client, final ModifiableGameState data) {
        return new SelectCallback(client, data);
    }


    /**
     * Create a callback from a swapRequestMsg callback.
     * @param client The receiver of the callback.
     * @param data The entire puzzle as is.
     * @return the callback.
     */
    public static CtxCallback swapRequestMsg(final GameClient client, final ModifiableGameState data) {
        return new SwapCallback(client, data);
    }


    /**
     * Create a callback from a rematchMsg callback.
     * @param client The receiver of the callback.
     * @param data The entire puzzle as is.
     * @return the callback.
     */
    public static CtxCallback rematchMsg(final GameClient client, final ModifiableGameState data) {
        return new RematchCallback(client, data);
    }
}
