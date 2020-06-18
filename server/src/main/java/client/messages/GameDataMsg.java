package client.messages;

import model.Tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameDataMsg extends GenericMsg {
    private List<Tile> puzzle;
    private Set<String> players;


    public GameDataMsg(final String sender, final List<Tile> puzzle, final Set<String> players) {
        super(sender);
        this.puzzle = puzzle;
        this.players = players;
    }

    public List<Tile> getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(final List<Tile> puzzle) {
        this.puzzle = puzzle;
    }

    public Set<String> getPlayers() {
        return players;
    }

    public void setPlayers(final Set<String> players) {
        this.players = players;
    }
}
