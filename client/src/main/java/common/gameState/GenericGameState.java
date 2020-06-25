package common.gameState;

import common.model.Player;
import common.model.Puzzle;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is an abstraction in common between ModifiableGameState and
 * ReadableGameState.
 */
public abstract class GenericGameState implements GameState {

    /**
     * The entire tiles.
     */
    private Puzzle puzzle;

    /**
     * All players.
     */
    private Set<Player> players;


    GenericGameState() {
        this.players = new HashSet<>();
    }


    protected final void setPuzzle(final Puzzle puzzle) {
        this.puzzle = puzzle;
    }


    @Override
    public final Puzzle getPuzzle() {
        return puzzle;
    }

    protected final void setPlayers(final Set<Player> players) {
        this.players = players;
    }


    @Override
    public final Set<Player> getPlayers() {
        return players;
    }


    @Override
    public final boolean isFinished() {
        return puzzle.isFinished();
    }
}
