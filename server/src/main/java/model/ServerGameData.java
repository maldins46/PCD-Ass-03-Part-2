package model;

import common.client.config.Destinations;
import common.client.messages.GameDataMsg;
import common.model.GameData;
import common.model.Tile;

import java.util.Collections;

public final class ServerGameData extends GameData {

    public ServerGameData() {
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            Tile tile = new Tile(i);
            getPuzzle().add(tile);
        }

        rematch();
    }


    public void addPlayer(final String playerName) {
        getPlayers().add(playerName);
    }


    public void removePlayer(final String playerName) {
        getPlayers().remove(playerName);

        for (Tile tile : getPuzzle()) {
            if (tile.getSelectorPlayer().equals(playerName)) {
                tile.setSelectorPlayer(null);
            }
        }
    }


    public void rematch() {
        if (getWin()) {
            shufflePuzzle();
        }
    }


    private void shufflePuzzle() {
        Collections.shuffle(getPuzzle());

        for (int i = 0; i < PUZZLE_SIZE; i++) {
            getPuzzle().get(i).setCurrentPosition(i);
        }

        getPuzzle().sort((o1, o2) -> Integer.compare(o1.getOriginalPosition(), o2.getCurrentPosition()));

        setWin(false);
    }


    public void setTileAsSelected(final int tileOriginaPos, final int tileCurrentPos, final String player) {
        final Tile currTile = getPuzzle().get(tileOriginaPos);

        if (currTile.getSelectorPlayer() != null
                && currTile.getCurrentPosition() == tileCurrentPos
                && getPlayers().contains(player)) {
            currTile.setSelectorPlayer(player);
        }
    }


    public void swapTile(final int tile1OriginaPos, final int tile1CurrentPos,
                         final int tile2OriginaPos, final int tile2CurrentPos,
                         final String player) {
        final Tile firstTile = getPuzzle().get(tile1OriginaPos);
        final Tile secondTile = getPuzzle().get(tile2OriginaPos);

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
        setWin(getPuzzle().stream().allMatch(x -> x.getCurrentPosition() == x.getOriginalPosition()));
    }

    /* todo in ClientGameData
    public void updateData(final GameDataMsg updatedData) {
        setPuzzle(updatedData.getPuzzle());
        setPlayers(updatedData.getPlayers());
        setWin(updatedData.getWin());
    }
    */


    public GameDataMsg generateGameDataMsg() {
        return new GameDataMsg(Destinations.SERVER_QUEUE_NAME, getPuzzle(), getPlayers(), getWin());
    }
}
