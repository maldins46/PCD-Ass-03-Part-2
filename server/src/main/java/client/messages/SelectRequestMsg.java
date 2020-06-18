package client.messages;

import model.Tile;

public final class SelectRequestMsg implements Message {
    private Tile selectedTile;

    public Tile getSelectedTile() {
        return selectedTile;
    }

    public void setSelectedTile(final Tile selectedTile) {
        this.selectedTile = selectedTile;
    }
}
