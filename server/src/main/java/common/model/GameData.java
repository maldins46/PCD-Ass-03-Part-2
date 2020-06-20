package common.model;

import java.util.*;

public abstract class GameData {
    protected static final int PUZZLE_WIDTH = 4;
    protected static final int PUZZLE_HEIGHT = 5;
    protected static final int PUZZLE_SIZE = PUZZLE_WIDTH * PUZZLE_HEIGHT;

    private List<Tile> puzzle = new ArrayList<>();
    private Set<String> players = new HashSet<>();

    /**
     * The variable is set to true when all tiles have the
     * right position in the puzzle.
     */
    private boolean win = true;

    protected final void setPuzzle(final List<Tile> puzzle) {
        this.puzzle = puzzle;
    }

    protected final List<Tile> getPuzzle() {
        return puzzle;
    }

    protected final void setPlayers(final Set<String> players) {
        this.players = players;
    }

    protected final Set<String> getPlayers() {
        return players;
    }

    protected final boolean getWin() {
        return win;
    }

    protected final void setWin(final boolean win) {
        this.win = win;
    }
}
