package client.messages;

import model.Tile;

public final class SwapRequestMsg implements Message {
    private Tile firstTile;
    private Tile secondTile;

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
