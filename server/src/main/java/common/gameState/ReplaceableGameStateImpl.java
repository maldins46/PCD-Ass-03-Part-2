package common.gameState;

import common.client.messages.GameStateMsg;

/**
 * todo
 */
final class ReplaceableGameStateImpl extends GameState implements ReplaceableGameState {

    /**
     * todo
     */
    ReplaceableGameStateImpl() {
        super();
    }

    @Override
    public void updateData(final GameStateMsg updatedData) {
        setWin(updatedData.getGameState().getWin());
        setPuzzle(updatedData.getGameState().getPuzzle());
        setPlayers(updatedData.getGameState().getPlayers());
    }
}
