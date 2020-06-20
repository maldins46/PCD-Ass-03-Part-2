package common.gameState;

import common.model.Player;
import common.model.Puzzle;

import java.util.Set;

public interface ReadableGameState {
    Puzzle getPuzzle();

    Set<Player> getPlayers();

    boolean getWin();
}
