package common.gameState;

import common.amqp.messages.GameStateMsg;
import common.amqp.messages.Messages;
import common.model.Player;
import common.model.Puzzle;
import common.model.Tile;

import java.util.Collections;
import java.util.Optional;

/**
 * Server of the game. This class has the privilege to modify the tiles sort.
 */
final class PuzzleServiceGameStateImpl extends GenericGameState implements PuzzleServiceGameState {

    /**
     * Instantiates a ServerGameState. It's create and shuflle the puzzle.
     */
    PuzzleServiceGameStateImpl() {
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
        if (isFinished()) {
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
    }


    @Override
    public void setTileAsSelected(final Tile tile, final Player player) {
        final Tile localTile = getPuzzle().getTileFromPos(tile.getOriginalPos());

        if (localTile.equals(tile) && getPlayers().contains(player)
                && localTile.getSelector().equals(Player.empty())) {
            localTile.setSelector(player);
        }
    }


    @Override
    public void swapTiles(final Tile startTile, final Tile destTile, final Player player) {
        final Tile locStartTile = getPuzzle().getTileFromPos(startTile.getOriginalPos());
        final Tile locDestTile = getPuzzle().getTileFromPos(destTile.getOriginalPos());

        if (locStartTile.equals(startTile) && locDestTile.equals(destTile)
                && getPlayers().contains(player)
                && locStartTile.getSelector().equals(player)) {
            locStartTile.setCurrentPos(destTile.getCurrentPos());
            locStartTile.setSelector(Player.empty());
            locDestTile.setCurrentPos(startTile.getCurrentPos());
            locDestTile.setSelector(Player.empty());
        }
    }

    @Override
    public GameStateMsg generateGameDataMsg() {
        return Messages.createGameStateMsg(getPuzzle(), getPlayers());
    }
}
