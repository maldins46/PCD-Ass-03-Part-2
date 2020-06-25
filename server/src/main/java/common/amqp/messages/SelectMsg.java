package common.amqp.messages;

import common.model.Player;
import common.model.Tile;

/**
 * It's used when a player select the first tile.
 */
public final class SelectMsg extends GenericPlayerMsg {

    /**
     * The tile selected.
     */
    private final Tile selectedTile;

    /**
     * The player selected.
     */
    private final Player selector;


    SelectMsg(final Tile selectedTile, final Player selector) {
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
