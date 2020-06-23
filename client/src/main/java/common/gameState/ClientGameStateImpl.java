package common.gameState;

import common.client.messages.GameStateMsg;
import common.model.Puzzle;

/**
 * Client instance that can play to the game. Clients can only clicks to the
 * tiles but only server do the swap.
 */
final class ClientGameStateImpl extends GenericGameState implements ClientGameState {

    /**
     * Constructor for the class.
     */
    ClientGameStateImpl() {
        super();
        setPuzzle(Puzzle.of());
    }

    @Override
    public void updateData(final GameStateMsg updatedData) {
        setWin(updatedData.isWin());
        setPuzzle(updatedData.getPuzzle());
        setPlayers(updatedData.getPlayers());
    }
}
