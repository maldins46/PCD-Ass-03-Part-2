package client.messages;

import model.Tile;

public final class SelectMsg extends GenericMsg {
    private Tile selectedTile;


    public SelectMsg(final String sender, final Tile selectedTile) {
        super(sender);
        this.selectedTile = selectedTile;
    }


    public Tile getSelectedTile() {
        return selectedTile;
    }


    public void setSelectedTile(final Tile selectedTile) {
        this.selectedTile = selectedTile;
    }
}
