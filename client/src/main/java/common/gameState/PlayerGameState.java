package common.gameState;

import common.amqp.messages.GameStateMsg;
import common.model.Player;
import common.model.Tile;

/**
 * This is the game state that client can do.
 */
public interface PlayerGameState extends GameState {

    /**
     * Update information of the game.
     * @param updatedData The updated's data.
     */
    void updateData(GameStateMsg updatedData);

    boolean isGameJoined();

    Player getCurrentPlayer();

    Tile getSelectedTile();

    boolean isPlayerSelector();

    void setCurrentPlayer(String playerName);
}
