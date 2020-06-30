package common.model;


import java.util.Objects;

/**
 * Class that models the concept of tile. A tile is a piece of the puzzle, and
 * it is characterized by the original and the current position.
 */
public final class Tile {

    /**
     * The initial position of the tile inside the puzzle, and the correct one.
     */
    private final int originalPos;

    /**
     * The current position of the tile inside the puzzle.
     */
    private int currentPos;

    /**
     * Memorizes the player that selected the tile. It is set to
     * Player.empty(), if no one selected it.
     */
    private Player selector;


    /**
     * The constructor creates the tile, placing it in its original position.
     * @param originalPos The original position in the puzzle.
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
     * Checks whether the tile is in the correct position inside the puzzle.
     * @return True, if the tile is in the correct position. False otherwise.
     */
    public boolean isInCorrectPosition() {
        return originalPos == currentPos;
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


    /**
     * Factory for a tile, initialized at a given position.
     * @param originalPos The original position of the tile.
     * @return The freshly created tile.
     */
    public static Tile of(final int originalPos) {
        return new Tile(originalPos);
    }
}
