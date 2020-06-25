package common.gameState;

import common.amqp.messages.GameStateMsg;
import common.model.Player;
import common.model.Puzzle;
import common.model.Tile;


/**
 * Implementation of the poorly-modifiable game state used by the players.
 */
final class PlayerGameStateImpl extends GenericGameState implements PlayerGameState {

    /**
     * Memorizes the player that correspond to the current player module.
     */
    private Player currentPlayer;


    /**
     * Default constructor, it calls initialization of its superclass and initializes
     * the puzzle with a blank set of tile.
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
        return getPuzzle().getTiles().stream()
                .anyMatch(x -> x.getSelector().equals(currentPlayer));
    }


    @Override
    public void setCurrentPlayer(String playerName) {
        this.currentPlayer = Player.of(playerName);
    }
}
