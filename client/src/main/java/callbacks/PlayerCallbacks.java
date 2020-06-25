package callbacks;

import common.amqp.client.PlayerClient;
import common.gameState.PlayerGameState;
import gui.PlayerGui;

/**
 * Factory used to create all callbacks needed my the client.
 */
public final class PlayerCallbacks {

    private PlayerCallbacks() { }


    /**
     * Creates a callback to handle a GameStateMsg from the puzzle service.
     * @param client The client resource.
     * @param gameState The game state resource.
     * @param gui The GUI accessor resource.
     * @return The callback.
     */
    public static GameStateCallback gameStateMsg(final PlayerClient client, final PlayerGameState gameState, final PlayerGui gui) {
        return new GameStateCallback(client, gameState, gui);
    }


    /**
     * Creates a callback to handle a AckMsg from the puzzle service.
     * @param client The client resource.
     * @param gameState The game state resource.
     * @param gui The GUI accessor resource.
     * @return The callback.
     */
    public static AckCallback ackMsg(final PlayerClient client, final PlayerGameState gameState, final PlayerGui gui) {
        return new AckCallback(client, gameState, gui);
    }
}
