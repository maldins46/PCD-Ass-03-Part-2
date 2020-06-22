package common.client.messages;

import common.model.Player;
import common.model.Tile;

public final class SwapMsg extends GenericMsg {
    private final Tile startTile;
    private final Tile destTile;
    private final Player selector;


    SwapMsg(final String sender, final Tile startTile, final Tile destTile, final Player selector) {
        super(sender);
        this.startTile = startTile;
        this.destTile = destTile;
        this.selector = selector;
    }


    public Tile getStartTile() {
        return startTile;
    }

    public Tile getDestTile() {
        return destTile;
    }

    public Player getSelector() {
        return selector;
    }
}
