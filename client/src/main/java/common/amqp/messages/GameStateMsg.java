package common.amqp.messages;

import common.model.Player;
import common.model.Puzzle;

import java.util.Set;

/**
 * Used for update the puzzle.
 */
public final class GameStateMsg implements PuzzleServiceMsg {
    /**
     * The game's puzzle.
     */
    private final Puzzle puzzle;

    /**
     * Players in the game.
     */
    private final Set<Player> players;


    GameStateMsg(final Puzzle puzzle, final Set<Player> players) {
        this.puzzle = puzzle;
        this.players = players;
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
}
