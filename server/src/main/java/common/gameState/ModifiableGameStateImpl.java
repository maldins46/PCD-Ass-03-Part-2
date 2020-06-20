package common.gameState;

import common.client.config.Destinations;
import common.client.messages.GameStateMsg;
import common.model.Tile;

import java.util.Collections;

/**
 * todo
 */
final class ModifiableGameStateImpl extends GameState implements ModifiableGameState {

    /**
     * todo
     * @param puzzleWidth
     * @param puzzleHeight
     */
    ModifiableGameStateImpl(final int puzzleWidth, final int puzzleHeight) {
        super(puzzleWidth, puzzleHeight);
        for (int i = 0; i < puzzleSize; i++) {
            Tile tile = new Tile(i);
            getPuzzle().add(tile);
        }

        rematch();
    }


    @Override
    public void addPlayer(final String player) {
        getPlayers().add(player);
    }


    @Override
    public void removePlayer(final String player) {
        getPlayers().remove(player);

        for (Tile tile : getPuzzle()) {
            if (tile.getSelectorPlayer().equals(player)) {
                tile.setSelectorPlayer(null);
            }
        }
    }


    @Override
    public void rematch() {
        if (getWin()) {
            shufflePuzzle();
        }
    }


    /**
     * todo
     */
    private void shufflePuzzle() {
        Collections.shuffle(getPuzzle());

        for (int i = 0; i < puzzleSize; i++) {
            getPuzzle().get(i).setCurrentPosition(i);
        }

        getPuzzle().sort((o1, o2) -> Integer.compare(o1.getOriginalPosition(), o2.getCurrentPosition()));

        setWin(false);
    }


    @Override
    public void setTileAsSelected(final Tile tile, final String player) {
        final Tile localTile = getPuzzle().get(tile.getOriginalPosition());

        if (localTile.getSelectorPlayer() != null
                && localTile.getCurrentPosition() == tile.getCurrentPosition()
                && getPlayers().contains(player)) {
            localTile.setSelectorPlayer(player);
        }
    }


    @Override
    public void swapTiles(final Tile startTile, final Tile destTile, final String player) {
        final Tile localStartTile = getPuzzle().get(startTile.getOriginalPosition());
        final Tile localDestTile = getPuzzle().get(destTile.getOriginalPosition());

        if (localStartTile.getCurrentPosition() == startTile.getCurrentPosition()
                && localDestTile.getCurrentPosition() == destTile.getCurrentPosition()
                && localStartTile.getSelectorPlayer().equals(player)) {
            localStartTile.setSelectorPlayer(null);
            localDestTile.setSelectorPlayer(null);
            localStartTile.setCurrentPosition(startTile.getCurrentPosition());
            localDestTile.setCurrentPosition(destTile.getCurrentPosition());
        }
        updateWin();
    }


    /**
     * todo
     */
    private void updateWin() {
        setWin(getPuzzle().stream().allMatch(x -> x.getCurrentPosition() == x.getOriginalPosition()));
    }


    @Override
    public GameStateMsg generateGameDataMsg() {
        return new GameStateMsg(Destinations.SERVER_QUEUE_NAME, this);
    }
}
