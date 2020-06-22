package common.gameState;

import common.model.Player;
import common.model.Puzzle;

import java.util.Set;

/**
 * Common methods between client and server.
 */
public interface ReadableGameState {

    /**
     * Getter for puzzle.
     * @return The puzzle.
     */
    Puzzle getPuzzle();


    /**
     * Getter for players.
     * @return All players.
     */
    Set<Player> getPlayers();

    /**
     * Getter hat checks if a match is finished.
     * @return True if a match is finished, false otherwise.
     */
    boolean getWin();
}
