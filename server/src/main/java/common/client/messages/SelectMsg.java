package common.client.messages;

import common.model.Player;
import common.model.Tile;

/**
 * It's used when a player select the first tile.
 */
public final class SelectMsg extends GenericMsg {

    /**
     * The tile selected.
     */
    private final Tile selectedTile;

    /**
     * The player selected.
     */
    private final Player selector;


    SelectMsg(final String sender, final Tile selectedTile, final Player selector) {
        super(sender);
        this.selectedTile = selectedTile;
        this.selector = selector;
    }


    /**
     * Getter for the selected tile.
     * @return The tile selected.
     */
    public Tile getSelectedTile() {
        return selectedTile;
    }


    /**
     * Getter for the player that selected the tile.
     * @return The player.
     */
    public Player getSelector() {
        return selector;
    }
}
