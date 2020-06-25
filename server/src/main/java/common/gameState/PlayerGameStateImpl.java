package common.gameState;

import common.amqp.messages.GameStateMsg;
import common.model.Player;
import common.model.Puzzle;
import common.model.Tile;

/**
 * Client instance that can play to the game. Clients can only clicks to the
 * tiles but only server do the swap.
 */
final class PlayerGameStateImpl extends GenericGameState implements PlayerGameState {

    private Player currentPlayer;

    /**
     * Constructor for the class.
     */
    PlayerGameStateImpl() {
        super();
        setPuzzle(Puzzle.of());
    }


    @Override
    public void updateData(final GameStateMsg updatedData) {
        setPuzzle(updatedData.getPuzzle());
        setPlayers(updatedData.getPlayers());
    }



    @Override
    public boolean isGameJoined() {
        return getPlayers().contains(currentPlayer);
    }


    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public Tile getSelectedTile() {
        return getPuzzle().getTiles().stream()
                .filter(x -> x.getSelector().equals(currentPlayer))
                .findFirst()
                .get();
    }

    @Override
    public boolean isPlayerSelector() {
        return getPuzzle().getTiles().stream().anyMatch(x -> x.getSelector().equals(currentPlayer));
    }


    @Override
    public void setCurrentPlayer(String playerName) {
        this.currentPlayer = Player.of(playerName);
    }
}
