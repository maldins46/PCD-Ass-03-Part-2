package common.gameState;

import common.client.config.Destinations;
import common.client.messages.GameStateMsg;
import common.client.messages.Messages;
import common.model.Player;
import common.model.Tile;

import java.util.Collections;
import java.util.Optional;

/**
 * Server of the game. This class has the privilege to modify the tiles sort.
 */
final class ServerGameState extends GameState implements ModifiableGameState {

    /**
     * Instantiates a ServerGameState. It's create and shuflle the puzzle.
     */
    ServerGameState() {
        super();

        for (int i = 0; i < getPuzzle().getSize(); i++) {
            Tile tile = new Tile(i);
            getPuzzle().getTiles().add(tile);
        }

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
                tile.setSelector(Player.generateEmpty());
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

        for (int i = 0; i < getPuzzle().getSize(); i++) {
            getPuzzle().getTiles().get(i).setCurrentPosition(i);
        }

        getPuzzle().getTiles().sort((o1, o2) -> Integer.compare(o1.getOriginalPosition(), o2.getCurrentPosition()));
        setWin(false);
    }


    @Override
    public void setTileAsSelected(final Tile tile, final Player player) {
        final Optional<Tile> locTileOpt = getPuzzle().getTileFromOriginalPos(tile.getOriginalPosition());

        if (locTileOpt.isPresent()) {
            final Tile locTile = locTileOpt.get();
            locTile.setSelector(player);
        }
    }


    @Override
    public void swapTiles(final Tile startTile, final Tile destTile, final Player player) {
        final Optional<Tile> locStartTileOpt = getPuzzle().getTileFromOriginalPos(startTile.getOriginalPosition());
        final Optional<Tile> locDestTileOpt = getPuzzle().getTileFromOriginalPos(destTile.getOriginalPosition());

        if (locDestTileOpt.isPresent()
                && locStartTileOpt.isPresent()
                && startTile.getSelector().equals(player)) {
            final Tile locStartTile = locStartTileOpt.get();
            final Tile locDestTile = locDestTileOpt.get();

            locStartTile.setSelector(Player.generateEmpty());
            locDestTile.setSelector(Player.generateEmpty());
            locStartTile.setCurrentPosition(destTile.getCurrentPosition());
            locDestTile.setCurrentPosition(startTile.getCurrentPosition());
        }
        updateWin();
    }


    /**
     * Check if the puzzle have all tiles in the original positions.
     */
    private void updateWin() {
        setWin(getPuzzle().getTiles().stream().allMatch(x -> x.getCurrentPosition() == x.getOriginalPosition()));
    }


    @Override
    public GameStateMsg generateGameDataMsg() {
        return Messages.createGameStateMsg(Destinations.SERVER_QUEUE_NAME, getPuzzle(), getPlayers(), getWin());
    }
}
