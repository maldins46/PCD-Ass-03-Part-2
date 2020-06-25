package common.gameState;

import common.amqp.messages.GameStateMsg;
import common.model.Player;
import common.model.Tile;

/**
 * The representation of the game state used by the player. Its core
 * characteristics can only be overwrote with data from the puzzle service,
 * meanwhile some special getters give access to subsets of the information.
 */
public interface PlayerGameState extends GameState {

    /**
     * Updates the core information relative to the match, by using a game
     * state message.
     * @param updatedData A message that brings updated data.
     */
    void updateData(GameStateMsg updatedData);


    /**
     * Checks if the current player is included inside the game players set from
     * the server.
     * @return True if the current player is part of the game, false otherwise.
     */
    boolean isGameJoined();


    /**
     * Returns the player instance assigned to the current player module.
     * @return The player instance assigned to the current player module.
     */
    Player getCurrentPlayer();


    /**
     * Searches inside the puzzle structure the first tile found selected
     * from the current player. If nothing is selected, returns null.
     * @return The first tile selected from the current player. Null if not
     *         present.
     */
    Tile getSelectedTile();


    /**
     * Checks whether the current player selected some tiles.
     * @return True if the current player selected some tiles, false otherwise.
     */
    boolean isPlayerSelector();


    /**
     * Setter for the current player field. In fact, it should be set
     * externally.
     * @param playerName The current player name.
     */
    void setCurrentPlayer(String playerName);
}
