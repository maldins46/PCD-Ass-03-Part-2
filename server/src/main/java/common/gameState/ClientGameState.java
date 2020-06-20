package common.gameState;

import common.client.messages.GameStateMsg;

/**
 * todo
 */
final class ClientGameState extends GameState implements ReplaceableGameState, ReadableGameState {

    /**
     * todo
     */
    ClientGameState() {
        super();
    }

    @Override
    public void updateData(final GameStateMsg updatedData) {
        setWin(updatedData.getGameState().getWin());
        setPuzzle(updatedData.getGameState().getPuzzle());
        setPlayers(updatedData.getGameState().getPlayers());
    }
}
