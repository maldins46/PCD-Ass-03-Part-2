package common.client.messages;

import common.model.Player;
import common.model.Tile;

public final class SelectMsg extends GenericMsg {
    private Tile selectedTile;
    private Player selector;


    public SelectMsg(final String sender, final Tile selectedTile, final Player selector) {
        super(sender);
        this.selectedTile = selectedTile;
        this.selector = selector;
    }


    public Tile getSelectedTile() {
        return selectedTile;
    }


    public Player getSelector() {
        return selector;
    }
}
