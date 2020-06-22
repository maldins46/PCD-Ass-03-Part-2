package common.gameState;

import common.client.config.Destinations;
import common.client.messages.GameStateMsg;
import common.client.messages.Messages;
import common.model.Player;
import common.model.Tile;

import java.util.Collections;

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
        final Tile localTile = getPuzzle().getTiles().get(tile.getOriginalPosition());

        if (localTile.getSelector() != null
                && localTile.getCurrentPosition() == tile.getCurrentPosition()
                && getPlayers().contains(player)) {
            localTile.setSelector(player);
        }
    }


    @Override
    public void swapTiles(final Tile startTile, final Tile destTile, final Player player) {
        final Tile localStartTile = getPuzzle().getTiles().get(startTile.getOriginalPosition());
        final Tile localDestTile = getPuzzle().getTiles().get(destTile.getOriginalPosition());

        if (localStartTile.getCurrentPosition() == startTile.getCurrentPosition()
                && localDestTile.getCurrentPosition() == destTile.getCurrentPosition()
                && localStartTile.getSelector().equals(player)) {
            localStartTile.setSelector(Player.generateEmpty());
            localDestTile.setSelector(Player.generateEmpty());
            localStartTile.setCurrentPosition(startTile.getCurrentPosition());
            localDestTile.setCurrentPosition(destTile.getCurrentPosition());
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
