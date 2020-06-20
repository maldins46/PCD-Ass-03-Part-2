package common.client.messages;

import common.model.Tile;

public final class SelectMsg extends GenericMsg {
    private Tile selectedTile;
    private String selectorPlayer;


    public SelectMsg(final String sender, final Tile selectedTile, final String selectorPlayer) {
        super(sender);
        this.selectedTile = selectedTile;
        this.selectorPlayer = selectorPlayer;
    }


    public Tile getSelectedTile() {
        return selectedTile;
    }


    public void setSelectedTile(final Tile selectedTile) {
        this.selectedTile = selectedTile;
    }

    public String getSelectorPlayer() {
        return selectorPlayer;
    }

    public void setSelectorPlayer(String selectorPlayer) {
        this.selectorPlayer = selectorPlayer;
    }
}
