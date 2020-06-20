package common.gameState;

import common.model.Tile;

import java.util.*;

public abstract class GameState {
    protected int puzzleWidth;
    protected int puzzleHeight;
    protected int puzzleSize;

    private List<Tile> puzzle = new ArrayList<>();
    private Set<String> players = new HashSet<>();


    public GameState(final int puzzleWidth, final int puzzleHeight) {
        this.puzzleWidth = puzzleWidth;
        this.puzzleHeight = puzzleHeight;
        this.puzzleSize = puzzleWidth * puzzleHeight;
    }

    public GameState() { }

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

    protected void setPuzzleWidth(final int puzzleWidth) {
        this.puzzleWidth = puzzleWidth;
    }


    protected int getPuzzleWidth() {
        return puzzleWidth;
    }

    protected void setPuzzleHeight(final int puzzleHeight) {
        this.puzzleHeight = puzzleHeight;
    }

    protected int getPuzzleHeight() {
        return puzzleHeight;
    }
}
