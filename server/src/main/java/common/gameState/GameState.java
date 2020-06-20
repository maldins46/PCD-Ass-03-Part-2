package common.gameState;

import common.model.Player;
import common.model.Puzzle;
import common.model.Tile;

import java.util.*;

public abstract class GameState {
    private Puzzle puzzle;
    private Set<Player> players;

    /**
     * The variable is set to true when all tiles have the
     * right position in the puzzle.
     */
    private boolean win = true;


    public GameState(final int puzzleWidth, final int puzzleHeight) {
        this.puzzle = new Puzzle(puzzleWidth, puzzleHeight);
        this.players = new HashSet<>();
    }

    public GameState() { }

    protected final void setPuzzle(final Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    protected final Puzzle getPuzzle() {
        return puzzle;
    }

    protected final void setPlayers(final Set<Player> players) {
        this.players = players;
    }

    protected final Set<Player> getPlayers() {
        return players;
    }

    protected final boolean getWin() {
        return win;
    }

    protected final void setWin(final boolean win) {
        this.win = win;
    }
}
