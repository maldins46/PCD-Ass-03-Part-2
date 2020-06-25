package common.amqp.messages;

import common.model.Player;
import common.model.Tile;

/**
 * Message used to indicate to the puzzle service that the player selected
 * a tile.
 */
public final class SelectMsg extends GenericPlayerMsg {

    /**
     * The selected tile.
     */
    private final Tile selectedTile;


    /**
     * Default constructor with field population.
     * @param selectedTile The selected tile.
     */
    SelectMsg(final Tile selectedTile) {
        this.selectedTile = selectedTile;
    }


    /**
     * Getter for the selected tile.
     * @return The selected tile.
     */
    public Tile getSelectedTile() {
        return selectedTile;
    }


    /**
     * The player that selected the tile corresponds to the sender field.
     * @return The player that selected the tile.
     */
    public Player getSelector() {
        return getSenderPlayer();
    }
}
