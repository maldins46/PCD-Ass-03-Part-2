package model;

public class Tile {
    private int originalPosition;
    private int currentPosition;

    // se null, nessun giocatore ha selezionato la casella
    private String selectorPlayer;

    public Tile() { }

    public Tile(final int originalPosition) {
        this.originalPosition = originalPosition;
        this.currentPosition = originalPosition;
    }

    public int getOriginalPosition() {
        return originalPosition;
    }

    public void setOriginalPosition(int originalPosition) {
        this.originalPosition = originalPosition;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getSelectorPlayer() {
        return selectorPlayer;
    }

    public void setSelectorPlayer(String selectorPlayer) {
        this.selectorPlayer = selectorPlayer;
    }
}
