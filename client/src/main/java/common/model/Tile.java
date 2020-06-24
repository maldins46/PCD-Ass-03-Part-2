package common.model;


import java.util.Objects;

/**
 * Tile of the puzzle.
 */
public final class Tile {

    /**
     * The right position of the tile.
     */
    private final int originalPos;


    /**
     * Current position.
     */
    private int currentPos;

    /**
     * Takes name of player that selected this element. Null if anyone have
     * selected this tile.
     */
    private Player selector;


    /**
     * Create a tile and set original and current positions to the original
     * position.
     * @param originalPos The original position.
     */
    public Tile(final int originalPos) {
        this.originalPos = originalPos;
        this.currentPos = originalPos;
        this.selector = Player.empty();
    }


    /**
     * Getter for original position.
     * @return the original position.
     */
    public int getOriginalPos() {
        return originalPos;
    }


    /**
     * Getter for current position.
     * @return the current position.
     */
    public int getCurrentPos() {
        return currentPos;
    }


    /**
     * Setter for the current position.
     * @param currentPos the current position.
     */
    public void setCurrentPos(final int currentPos) {
        this.currentPos = currentPos;
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

    /**
     * Check if a tile is this.tile.
     * @param tile The tile to check.
     * @return True if the tile parameter have the same parameter of this.
     */
    public boolean positionEquals(final Tile tile) {
        return this.currentPos == tile.currentPos && this.originalPos == tile.originalPos;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Tile tile = (Tile) o;
        return originalPos == tile.originalPos
                && currentPos == tile.currentPos
                && Objects.equals(selector, tile.selector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalPos, currentPos, selector);
    }
}
