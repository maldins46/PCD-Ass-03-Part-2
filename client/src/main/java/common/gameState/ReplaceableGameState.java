package common.gameState;

import common.client.messages.GameStateMsg;

/**
 * This is the game state that client can do.
 */
public interface ReplaceableGameState extends ReadableGameState {


    /**
     * Update information of the game.
     * @param updatedData The updated's data.
     */
    void updateData(GameStateMsg updatedData);
}
