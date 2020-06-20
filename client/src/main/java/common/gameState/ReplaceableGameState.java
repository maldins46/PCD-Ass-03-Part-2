package common.gameState;

import common.client.messages.GameStateMsg;

/**
 * todo
 */
public interface ReplaceableGameState extends ReadableGameState {
    /**
     * todo
     * @param updatedData
     */
    void updateData(GameStateMsg updatedData);
}
