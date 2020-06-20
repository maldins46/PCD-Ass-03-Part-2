package callbacks;

import client.CtxCallback;
import client.GameClient;
import model.GameData;

/**
 * Returns a Callback that is ...
 */
public final class Callbacks {

    private Callbacks() { }

    /**
     * Create a callback for the newPlayerMsg.
     * @param client The receiver of the callback.
     * @param data The entire puzzle as is.
     * @return the callback.
     */
    public static CtxCallback newPlayerMsg(final GameClient client, final GameData data) {
        return new NewPlayerCallback(client, data);
    }


    /**
     * Create a callback from a selectMsg callback.
     * @param client The receiver of the callback.
     * @param data The entire puzzle as is.
     * @return the callback.
     */
    public static CtxCallback selectMsg(final GameClient client, final GameData data) {
        return new SelectCallback(client, data);
    }


    /**
     * Create a callback from a swapRequestMsg callback.
     * @param client The receiver of the callback.
     * @param data The entire puzzle as is.
     * @return the callback.
     */
    public static CtxCallback swapRequestMsg(final GameClient client, final GameData data) {
        return new SwapCallback(client, data);
    }


    /**
     * Create a callback from a rematchMsg callback.
     * @param client The receiver of the callback.
     * @param data The entire puzzle as is.
     * @return the callback.
     */
    public static CtxCallback rematchMsg(final GameClient client, final GameData data) {
        return new RematchCallback(client, data);
    }
}
