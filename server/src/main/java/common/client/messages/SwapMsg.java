package common.client.messages;

import common.model.Player;
import common.model.Tile;

/**
 * Message that represent the swap of two tiles.
 */
public final class SwapMsg extends GenericMsg {

    /**
     * The first tile selected.
     */
    private final Tile startTile;

    /**
     * The second tile selected.
     */
    private final Tile destTile;

    /**
     * The selector player.
     */
    private final Player selector;


    /**
     * Create a swap message.
     * @param sender The message sender.
     * @param startTile The first tile selected.
     * @param destTile The second tile selected.
     * @param selector The player selector.
     */
    SwapMsg(final String sender, final Tile startTile, final Tile destTile,
            final Player selector) {
        super(sender);
        this.startTile = startTile;
        this.destTile = destTile;
        this.selector = selector;
    }


    /**
     * Getter for the first tile.
     * @return The first tile.
     */
    public Tile getStartTile() {
        return startTile;
    }

    /**
     * Getter for the second tile.
     * @return The second file.
     */
    public Tile getDestTile() {
        return destTile;
    }

    /**
     * Getter for the player that selected.
     * @return The player selected.
     */
    public Player getSelector() {
        return selector;
    }
}
