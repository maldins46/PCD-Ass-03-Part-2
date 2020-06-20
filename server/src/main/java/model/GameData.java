package model;

import client.config.Destinations;
import client.messages.GameDataMsg;

import java.util.*;

public final class GameData {
    public static final int PUZZLE_WIDTH = 4;
    public static final int PUZZLE_HEIGHT = 5;
    public static final int PUZZLE_SIZE = PUZZLE_WIDTH * PUZZLE_HEIGHT;

    private List<Tile> puzzle = new ArrayList<>();
    private Set<String> playerNames = new HashSet<>();

    /**
     * The variable is set to true when all tiles have the
     * right position in the puzzle.
     */
    private boolean win = true;


    public GameData() {
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            Tile tile = new Tile(i);
            puzzle.add(tile);
        }

        rematch();
    }


    public void addPlayer(final String playerName) {
        playerNames.add(playerName);
    }


    public void removePlayer(final String playerName) {
        playerNames.remove(playerName);

        for (Tile tile : puzzle) {
            if (tile.getSelectorPlayer().equals(playerName)) {
                tile.setSelectorPlayer(null);
            }
        }
    }


    public void rematch() {
        if (win) {
            shufflePuzzle();
        }
    }


    private void shufflePuzzle() {
        Collections.shuffle(puzzle);

        for (int i = 0; i < PUZZLE_SIZE; i++) {
            puzzle.get(i).setCurrentPosition(i);
        }

        puzzle.sort((o1, o2) -> Integer.compare(o1.getOriginalPosition(), o2.getCurrentPosition()));

        win = false;
    }


    public void setTileAsSelected(final int tileOriginaPos, final int tileCurrentPos, final String player) {
        final Tile currTile = puzzle.get(tileOriginaPos);

        if (currTile.getSelectorPlayer() != null
                && currTile.getCurrentPosition() == tileCurrentPos
                && playerNames.contains(player)) {
            currTile.setSelectorPlayer(player);
        }
    }


    public void swapTile(final int tile1OriginaPos, final int tile1CurrentPos,
                         final int tile2OriginaPos, final int tile2CurrentPos,
                         final String player) {
        final Tile firstTile = puzzle.get(tile1OriginaPos);
        final Tile secondTile = puzzle.get(tile2OriginaPos);

        if (firstTile.getCurrentPosition() == tile1CurrentPos
                && secondTile.getCurrentPosition() == tile2CurrentPos
                && firstTile.getSelectorPlayer().equals(player)) {
            firstTile.setSelectorPlayer(null);
            secondTile.setSelectorPlayer(null);
            firstTile.setCurrentPosition(tile2CurrentPos);
            secondTile.setCurrentPosition(tile1CurrentPos);
        }
        updateWin();
    }


    private void updateWin() {
        win = puzzle.stream().allMatch(x -> x.getCurrentPosition() == x.getOriginalPosition());
    }


    public void updateData(final GameDataMsg updatedData) {
        puzzle = updatedData.getPuzzle();
        playerNames = updatedData.getPlayers();
        win = updatedData.getWin();
    }


    public GameDataMsg generateGameDataMsg() {
        return new GameDataMsg(Destinations.SERVER_QUEUE_NAME, puzzle, playerNames, win);
    }
}
