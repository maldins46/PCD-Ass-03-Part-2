package common.amqp.messages;

import common.model.Player;
import common.model.Tile;

/**
 * Message used to indicate to the puzzle service to swap two tiles.
 */
public final class SwapMsg extends GenericPlayerMsg {

    /**
     * The first tile selected by the player.
     */
    private final Tile startTile;

    /**
     * The second tile selected by the player.
     */
    private final Tile destTile;


    /**
     * Default constructor with field population.
     * @param startTile The first tile selected by the player.
     * @param destTile The second tile selected by the player.
     */
    SwapMsg(final Tile startTile, final Tile destTile) {
        this.startTile = startTile;
        this.destTile = destTile;
    }


    /**
     * Getter for the first tile field.
     * @return The first tile selected by the player.
     */
    public Tile getStartTile() {
        return startTile;
    }


    /**
     * Getter for the second tile field.
     * @return The second tile selected by the player.
     */
    public Tile getDestTile() {
        return destTile;
    }


    /**
     * The player that wants to perform the swap corresponds to the sender
     * field.
     * @return The player that wants to perform the swap.
     */
    public Player getSelector() {
        return getSenderPlayer();
    }
}
