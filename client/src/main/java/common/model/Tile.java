package common.model;


/**
 * Tile of the puzzle.
 */
public final class Tile {

    /**
     * The right position of the tile.
     */
    private final int originalPosition;


    /**
     * Current position.
     */
    private int currentPosition;

    /**
     * Takes name of player that selected this element. Null if anyone have
     * selected this tile.
     */
    private Player selector;


    /**
     * Create a tile and set original and current positions to the original
     * position.
     * @param originalPosition The original position.
     */
    public Tile(final int originalPosition) {
        this.originalPosition = originalPosition;
        this.currentPosition = originalPosition;
        this.selector = Player.generateEmpty();
    }


    /**
     * Getter for original position.
     * @return the original position.
     */
    public int getOriginalPosition() {
        return originalPosition;
    }


    /**
     * Getter for current position.
     * @return the current position.
     */
    public int getCurrentPosition() {
        return currentPosition;
    }


    /**
     * Setter for the current position.
     * @param currentPosition the current position.
     */
    public void setCurrentPosition(final int currentPosition) {
        this.currentPosition = currentPosition;
    }


    /**
     * Getter for selector player.
     * @return the selector player is present.
     */
    public Player getSelector() {
        return selector;
    }


    /**
     * Setter for the selector player.
     * @param selector the selector player.
     */
    public void setSelector(final Player selector) {
        this.selector = selector;
    }
}
