package common.client.messages;

import common.model.Player;
import common.model.Puzzle;

import java.util.Set;

/**
 * Used for update the puzzle.
 */
public final class GameStateMsg extends GenericMsg {
    /**
     * The game's puzzle.
     */
    private final Puzzle puzzle;

    /**
     * Players in the game.
     */
    private final Set<Player> players;

    /**
     * Set state of the game if someone have won.
     */
    private final boolean win;


    GameStateMsg(final String sender, final Puzzle puzzle,
                 final Set<Player> players, final boolean win) {
        super(sender);
        this.puzzle = puzzle;
        this.players = players;
        this.win = win;
    }

    /**
     * Getter for the puzzle.
     * @return The puzzle.
     */
    public Puzzle getPuzzle() {
        return puzzle;
    }

    /**
     * Getter for the players.
     * @return The players.
     */
    public Set<Player> getPlayers() {
        return players;
    }

    /**
     * Getter for the win.
     * @return True if someone have won, false otherwise.
     */
    public boolean isWin() {
        return win;
    }
}
