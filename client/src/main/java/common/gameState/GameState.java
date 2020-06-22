package common.gameState;

import common.model.Player;
import common.model.Puzzle;

import java.util.HashSet;
import java.util.Set;

public abstract class GameState implements ReadableGameState {
    private Puzzle puzzle;
    private Set<Player> players;

    /**
     * The variable is set to true when all tiles have the
     * right position in the puzzle.
     */
    private boolean win = true;


    public GameState() {
        this.puzzle = Puzzle.of();
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
    public final boolean getWin() {
        return win;
    }

    protected final void setWin(final boolean win) {
        this.win = win;
    }
}
