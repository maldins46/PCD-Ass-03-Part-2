package common.gameState;

import common.client.messages.GameStateMsg;

/**
 * Client instance that can play to the game. Clients can only clicks to the
 * tiles but only server do the swap.
 */
final class ClientGameState extends GameState implements ReplaceableGameState, ReadableGameState {

    /**
     * Constructor for the class.
     */
    ClientGameState() {
        super();
    }

    @Override
    public void updateData(final GameStateMsg updatedData) {
        setWin(updatedData.isWin());

        getPuzzle().getTiles().clear();
        getPuzzle().getTiles().addAll(updatedData.getPuzzle().getTiles());

        getPlayers().clear();
        getPlayers().addAll(updatedData.getPlayers());
    }
}
