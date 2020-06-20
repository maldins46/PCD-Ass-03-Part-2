package common.client.messages;

import common.model.Tile;

public final class SwapMsg extends GenericMsg {
    private Tile startTile;
    private Tile destTile;
    private String selectorPlayer;


    public SwapMsg(final String sender, final Tile startTile, final Tile destTile, final String selectorPlayer) {
        super(sender);
        this.startTile = startTile;
        this.destTile = destTile;
        this.selectorPlayer = selectorPlayer;
    }


    public Tile getStartTile() {
        return startTile;
    }

    public void setStartTile(final Tile startTile) {
        this.startTile = startTile;
    }

    public Tile getDestTile() {
        return destTile;
    }

    public void setDestTile(final Tile destTile) {
        this.destTile = destTile;
    }

    public String getSelectorPlayer() {
        return selectorPlayer;
    }

    public void setSelectorPlayer(final String selectorPlayer) {
        this.selectorPlayer = selectorPlayer;
    }
}
