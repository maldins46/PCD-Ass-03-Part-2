package common.gameState;

import common.model.Player;
import common.model.Puzzle;

import java.util.HashSet;
import java.util.Set;

/**
 * Abstraction in common between player and puzzle service game state, that
 * implements some common features.
 */
public abstract class GenericGameState implements GameState {

    /**
     * The puzzle instance of the game.
     */
    private Puzzle puzzle;

    /**
     * Keeps track of the players connected to the game.
     */
    private Set<Player> players = new HashSet<>();


    @Override
    public final Set<Player> getPlayers() {
        return players;
    }


    @Override
    public final boolean isFinished() {
        return puzzle.isFinished();
    }


    @Override
    public final Puzzle getPuzzle() {
        return puzzle;
    }


    /**
     * Gives to subclasses access to the puzzle instance.
     * @param puzzle The puzzle instance.
     */
    protected final void setPuzzle(final Puzzle puzzle) {
        this.puzzle = puzzle;
    }


    /**
     * Gives to subclasses access to the players instance.
     * @param players The players instance.
     */
    protected final void setPlayers(final Set<Player> players) {
        this.players = players;
    }
}
