package client.messages;

import model.Tile;

public final class SwapMsg extends GenericMsg {
    private Tile firstTile;
    private Tile secondTile;


    public SwapMsg(final String sender, final Tile firstTile, final Tile secondTile) {
        super(sender);
        this.firstTile = firstTile;
        this.secondTile = secondTile;
    }


    public Tile getFirstTile() {
        return firstTile;
    }

    public void setFirstTile(final Tile firstTile) {
        this.firstTile = firstTile;
    }

    public Tile getSecondTile() {
        return secondTile;
    }

    public void setSecondTile(final Tile secondTile) {
        this.secondTile = secondTile;
    }
}
