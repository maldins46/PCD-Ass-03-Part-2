package common.client.messages;

import common.model.Player;
import common.model.Puzzle;

import java.util.Set;

public final class GameStateMsg extends GenericMsg {
    private final Puzzle puzzle;
    private final Set<Player> players;
    private final boolean win;


    GameStateMsg(String sender, Puzzle puzzle, Set<Player> players, boolean win) {
        super(sender);
        this.puzzle = puzzle;
        this.players = players;
        this.win = win;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public boolean isWin() {
        return win;
    }
}
