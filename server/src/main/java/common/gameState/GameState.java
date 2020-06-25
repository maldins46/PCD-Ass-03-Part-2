package common.gameState;

import common.model.Player;
import common.model.Puzzle;

import java.util.Set;

/**
 * Interface that represents the state of the game, defining its model
 * objects in a given moment, and giving the possibility to read them.
 */
public interface GameState {

    /**
     * Returns the puzzle instance of the game.
     * @return The puzzle instance.
     */
    Puzzle getPuzzle();


    /**
     * Returns the set of players connected to the game.
     * @return The set of players connected to the game.
     */
    Set<Player> getPlayers();


    /**
     * Checks whether the current match is finished, checking the position of
     * the tiles.
     * @return True if the match is finished, false otherwise.
     */
    boolean isFinished();
}
