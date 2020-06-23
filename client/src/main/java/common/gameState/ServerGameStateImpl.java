package common.gameState;

import common.client.config.Destinations;
import common.client.messages.GameStateMsg;
import common.client.messages.Messages;
import common.model.Player;
import common.model.Puzzle;
import common.model.Tile;

import java.util.Collections;
import java.util.Optional;

/**
 * Server of the game. This class has the privilege to modify the tiles sort.
 */
final class ServerGameStateImpl extends GenericGameState implements ServerGameState {

    /**
     * Instantiates a ServerGameState. It's create and shuflle the puzzle.
     */
    ServerGameStateImpl() {
        super();
        setPuzzle(Puzzle.of(true));
        rematch();
    }


    @Override
    public void addPlayer(final Player player) {
        getPlayers().add(player);
    }


    @Override
    public void removePlayer(final Player player) {
        getPlayers().remove(player);

        for (Tile tile : getPuzzle().getTiles()) {
            if (tile.getSelector().equals(player)) {
                tile.setSelector(Player.empty());
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
     * Sort in casual order the tiles in the puzzle. Thanks to this method
     * client can't see in the game the real order.
     */
    private void shufflePuzzle() {
        Collections.shuffle(getPuzzle().getTiles());

        for (int i = 0; i < Puzzle.SIZE; i++) {
            getPuzzle().getTiles().get(i).setCurrentPos(i);
        }

        setWin(false);
    }


    @Override
    public void setTileAsSelected(final Tile tile, final Player player) {
        final Optional<Tile> localTile = getPuzzle().getTileFromPos(tile.getOriginalPos());
        localTile.ifPresent(x -> {
            if (x.equals(tile)
                    && getPlayers().contains(player)
                    && x.getSelector().equals(Player.empty())) {
                x.setSelector(player);
            }
        });
    }



    @Override
    public void swapTiles(final Tile startTile, final Tile destTile, final Player player) {
        final Optional<Tile> locStartTile = getPuzzle().getTileFromPos(startTile.getOriginalPos());
        final Optional<Tile> locDestTile = getPuzzle().getTileFromPos(destTile.getOriginalPos());

        locStartTile.ifPresent(x -> {
            locDestTile.ifPresent(y -> {
                if (x.equals(startTile) && y.equals(destTile)
                        && getPlayers().contains(player)
                        && x.getSelector().equals(player)) {
                    x.setCurrentPos(destTile.getCurrentPos());
                    x.setSelector(Player.empty());
                    y.setCurrentPos(startTile.getCurrentPos());
                    y.setSelector(Player.empty());
                    updateWin();
                }
            });
        });
    }


    /**
     * Check if the puzzle have all tiles in the original positions.
     */
    private void updateWin() {
        setWin(getPuzzle().getTiles().stream().allMatch(x -> x.getCurrentPos() == x.getOriginalPos()));
    }


    @Override
    public GameStateMsg generateGameDataMsg() {
        return Messages.createGameStateMsg(getPuzzle(), getPlayers(), getWin());
    }
}
