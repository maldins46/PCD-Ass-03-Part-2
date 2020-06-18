package model;

import client.config.Destinations;
import client.messages.GameDataMsg;

import java.util.*;
import java.util.stream.Collectors;

public final class GameData {
    public static final int PUZZLE_WIDTH = 4;
    public static final int PUZZLE_HEIGHT = 5;
    public static final int PUZZLE_SIZE = PUZZLE_WIDTH * PUZZLE_HEIGHT;

    // todo oggetto gestione stato gioco
    private List<Tile> puzzle = new ArrayList<>();
    private Set<String> playerNames = new HashSet<>();


    public GameData() {
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            Tile tile = new Tile(i);
            puzzle.add(tile);
        }

        shufflePuzzle();
    }


    public void addPlayer(final String playerName) {
        playerNames.add(playerName);
    }


    public void removePlayer(final String playerName) {
        playerNames.remove(playerName);

        for (Tile tile: puzzle) {
            if (tile.getSelectorPlayer().equals(playerName)) {
                tile.setSelectorPlayer(null);
            }
        }
    }



    public void rematch() {
        shufflePuzzle();
    }


    private void shufflePuzzle() {
        Collections.shuffle(puzzle);

        for (int i = 0; i < PUZZLE_SIZE; i++) {
            puzzle.get(i).setCurrentPosition(i);
        }

        puzzle.sort((o1, o2) -> {
            if (o1.getOriginalPosition() < o2.getCurrentPosition()) {
                return -1;
            } else if (o1.getOriginalPosition() > o2.getCurrentPosition()) {
                return 1;
            } else {
                return 0;
            }
        });
    }


    private void setTileAsSelected(final int tileOriginaPos, final int tileCurrentPos, final String player) {
        final Tile currTile = puzzle.get(tileOriginaPos);

        if (currTile.getSelectorPlayer() != null
            && currTile.getCurrentPosition() == tileCurrentPos
            && playerNames.contains(player)) {
            currTile.setSelectorPlayer(player);
        }
    }


    private void swapTile(final int tile1OriginaPos, final int tile1CurrentPos,
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
    }


    public void updateData(GameDataMsg updatedData) {
        puzzle = updatedData.getPuzzle();
        playerNames = updatedData.getPlayerNames();
    }


    public GameDataMsg generateGameDataMsg() {
        return new GameDataMsg(puzzle, playerNames);
    }
}
