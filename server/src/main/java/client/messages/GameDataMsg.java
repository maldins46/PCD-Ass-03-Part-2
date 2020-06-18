package client.messages;

import model.GameData;
import model.Tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameDataMsg implements Message {
    private List<Tile> puzzle = new ArrayList<>();
    private Set<String> playerNames = new HashSet<>();

    public GameDataMsg() { }

    public GameDataMsg(final List<Tile> puzzle, final Set<String> playerNames) {
        this.puzzle = puzzle;
        this.playerNames = playerNames;
    }

    public List<Tile> getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(final List<Tile> puzzle) {
        this.puzzle = puzzle;
    }

    public Set<String> getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(final Set<String> playerNames) {
        this.playerNames = playerNames;
    }
}
