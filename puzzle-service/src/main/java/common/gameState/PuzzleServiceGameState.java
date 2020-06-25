package common.gameState;

import common.amqp.messages.GameStateMsg;
import common.model.Player;
import common.model.Tile;

/**
 * The representation of the game state used by the puzzle service. The service
 * must have the possibility to do precise modifications to the structure.
 */
public interface PuzzleServiceGameState extends GameState {

    /**
     * Adds a player to the game.
     * @param player The player that have to be added.
     */
    void addPlayer(Player player);


    /**
     * Removes a player to the game.
     * @param player The player that have to be removed.
     */
    void removePlayer(Player player);


    /**
     * Initializes a new match inside the game. It basically shuffle current
     * positions of the tiles of the puzzle.
     */
    void rematch();


    /**
     * Sets a tile as selected from a player.
     * @param tile The selected tile.
     * @param player The player that selected the tile.
     */
    void setTileAsSelected(Tile tile, Player player);


    /**
     * Switches two tiles current position.
     * @param startTile The first tile.
     * @param destTile The second tile.
     * @param player The player that do the swap.
     */
    void swapTiles(Tile startTile, Tile destTile, Player player);


    /**
     * Creates a game state message, with the updated data.
     * @return The game state message.
     */
    GameStateMsg generateGameDataMsg();
}
