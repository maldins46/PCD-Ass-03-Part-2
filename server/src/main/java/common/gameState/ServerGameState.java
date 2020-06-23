package common.gameState;

import common.client.messages.GameStateMsg;
import common.model.Player;
import common.model.Tile;

/**
 * GameState used for server. The server is the only component that can swap
 * effectively the tiles and he is the one that check if clients have win.
 */
public interface ServerGameState extends GameState {

    /**
     * Add a player to the match.
     * @param player The player that have to be added.
     */
    void addPlayer(Player player);

    /**
     * Remove a player to the match.
     * @param player The player that have to be removed.
     */
    void removePlayer(Player player);

    /**
     * Init a new instance of the game.
     */
    void rematch();

    /**
     * Set a tile as selected and assign to this the player.
     * @param tile The tile selected.
     * @param player The player that have selected this tile.
     */
    void setTileAsSelected(Tile tile, Player player);

    /**
     * Switch two tiles.
     * @param startTile The first tile.
     * @param destTile The second tile.
     * @param player The player that do the swap.
     */
    void swapTiles(Tile startTile, Tile destTile, Player player);

    /**
     * Create a gameMsg with the updated data.
     * @return The game msg.
     */
    GameStateMsg generateGameDataMsg();
}
