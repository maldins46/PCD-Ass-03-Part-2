package callbacks;


import common.amqp.CtxCallback;
import common.amqp.client.PuzzleServiceClient;
import common.gameState.PuzzleServiceGameState;

/**
 * Factory that returns the callbacks used inside the server.
 */
public final class Callbacks {

    private Callbacks() { }

    /**
     * Create a callback for the newPlayerMsg.
     * @param client The receiver of the callback.
     * @param state The entire puzzle as is.
     * @return the callback.
     */
    public static CtxCallback newPlayerMsg(final PuzzleServiceClient client, final PuzzleServiceGameState state) {
        return new NewPlayerCallback(client, state);
    }


    /**
     * Create a callback from a selectMsg callback.
     * @param client The receiver of the callback.
     * @param state The entire puzzle as is.
     * @return the callback.
     */
    public static CtxCallback selectMsg(final PuzzleServiceClient client, final PuzzleServiceGameState state) {
        return new SelectCallback(client, state);
    }


    /**
     * Create a callback from a swapRequestMsg callback.
     * @param client The receiver of the callback.
     * @param state The entire puzzle as is.
     * @return the callback.
     */
    public static CtxCallback swapRequestMsg(final PuzzleServiceClient client, final PuzzleServiceGameState state) {
        return new SwapCallback(client, state);
    }


    /**
     * Create a callback from a rematchMsg callback.
     * @param client The receiver of the callback.
     * @param state The entire puzzle as is.
     * @return the callback.
     */
    public static CtxCallback rematchMsg(final PuzzleServiceClient client, final PuzzleServiceGameState state) {
        return new RematchCallback(client, state);
    }
}
