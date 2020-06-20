package common.gameState;

import common.client.messages.GameStateMsg;
import common.model.Tile;

/**
 * todo
 */
public interface ModifiableGameState {

    /**
     * todo
     * @param player
     */
    void addPlayer(String player);

    /**
     * todo
     * @param player
     */
    void removePlayer(String player);

    /**
     * todo
     */
    void rematch();

    /**
     * todo
     * @param tile
     * @param player
     */
    void setTileAsSelected(Tile tile, String player);

    /**
     * todo
     * @param startTile
     * @param destTile
     * @param player
     */
    void swapTiles(Tile startTile, Tile destTile, String player);

    /**
     * todo
     * @return
     */
    GameStateMsg generateGameDataMsg();
}
