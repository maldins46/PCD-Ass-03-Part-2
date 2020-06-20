package common.gameState;

import common.client.messages.GameStateMsg;

/**
 * todo
 */
public interface ReplaceableGameState {
    /**
     * todo
     * @param updatedData
     */
    void updateData(GameStateMsg updatedData);
}
