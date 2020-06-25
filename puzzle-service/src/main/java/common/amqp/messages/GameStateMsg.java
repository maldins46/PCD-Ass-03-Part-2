package common.amqp.messages;

import common.model.Player;
import common.model.Puzzle;

import java.util.Set;

/**
 * Message used to spread the game state information to the players.
 */
public final class GameStateMsg implements PuzzleServiceMsg {
    /**
     * Describes the state of the puzzle tiles.
     */
    private final Puzzle puzzle;

    /**
     * Describes which players are connected to the game.
     */
    private final Set<Player> players;


    /**
     * Default constructor with field population.
     * @param puzzle The puzzle instance.
     * @param players The player set.
     */
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
