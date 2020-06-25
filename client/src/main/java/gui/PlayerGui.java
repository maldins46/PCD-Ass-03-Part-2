package gui;

import common.amqp.client.PlayerClient;
import common.gameState.PlayerGameState;

/**
 * Represents an access point of modification for the GUI.
 */
public interface PlayerGui {

    /**
     * Starts the GUI visualization to the user.
     */
    void launch();

    /**
     * Called when all tiles are in the correct position. Disables clicks
     * on tiles, shows a label that indicates the match end, and enables
     * the new match button.
     */
    void finishMatch();


    /**
     * Called when the player is kicked out of the match, following the
     * expiration of a timeout. It disables the tiles and enables the
     * join button.
     */
    void leaveGame();


    /**
     * Called when the player just selected a tile, and wants to select a
     * second one to do a swap. The click in a tile, except for the selected
     * one, will originate a swap message to the puzzle service.
     */
    void setPuzzleSwappable();


    /**
     * Called when the player did not select a tile, and wants to select the
     * first one. The click in a tile, except for the ones selected by other
     * players, will originate a select message to the puzzle service.
     */
    void setPuzzleSelectable();


    /**
     * Called when an ack message arrives. It will enable click on tiles,
     * disabled when a message is sent.
     */
    void unlockPuzzle();


    /**
     * Factory used to hide the real implementation of the GUI.
     * @param client The GameClient in the game.
     * @param gameState The GameState of the game.
     * @return The implementation of this interface.
     */
    static PlayerGui of(final PlayerClient client, final PlayerGameState gameState) {
        return new PlayerGuiImpl(gameState, client);
    }
}
