package common.gameState;

import common.client.messages.GameStateMsg;
import common.model.Player;
import common.model.Tile;

/**
 * todo
 */
public interface ModifiableGameState extends ReadableGameState {

    /**
     * todo
     * @param player
     */
    void addPlayer(Player player);

    /**
     * todo
     * @param player
     */
    void removePlayer(Player player);

    /**
     * todo
     */
    void rematch();

    /**
     * todo
     * @param tile
     * @param player
     */
    void setTileAsSelected(Tile tile, Player player);

    /**
     * todo
     * @param startTile
     * @param destTile
     * @param player
     */
    void swapTiles(Tile startTile, Tile destTile, Player player);

    /**
     * todo
     * @return
     */
    GameStateMsg generateGameDataMsg();
}
