package common.gameState;

import common.client.messages.GameStateMsg;

/**
 * Client instance can only clicks to the puzzle without effectively changes
 * the puzzle. The component that swap tiles is ModifiableGameState that is
 * the server of the game.
 */
public interface ReplaceableGameState extends ReadableGameState {


    /**
     * Update the information of the client.
     * @param updatedData The information updated.
     */
    void updateData(GameStateMsg updatedData);
}
