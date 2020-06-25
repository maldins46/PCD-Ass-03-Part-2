package common.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class that models the concept of puzzle.
 */
public final class Puzzle {
    /**
     * Constant that defines how many tiles contains each row ot the puzzle.
     */
    public static final int WIDTH = 5;

    /**
     * Constant that defines how many tiles contains each column of the puzzle.
     */
    public static final int HEIGHT = 3;

    /**
     * Constant that defines how many tiles contains the puzzle.
     */
    public static final int SIZE = HEIGHT * WIDTH;


    /**
     * Memorizes all tiles of the puzzle.
     */
    private final List<Tile> tiles;


    /**
     * The constructor gives the possibility to initialize the tiles of the
     * puzzle, placing as much tiles as the size of it.
     * @param initializeTiles If true, the tile structure is initialized
     *                        with as much tiles as the size of it. Otherwise,
     *                        it is initialized empty.
     */
    private Puzzle(final boolean initializeTiles) {
        this.tiles = new ArrayList<>();

        if (initializeTiles) {
            for (int i = 0; i < Puzzle.SIZE; i++) {
                final Tile tile = Tile.of(i);
                this.tiles.add(tile);
            }
        }
    }


    /**
     * Getter for the puzzle tiles.
     * @return The puzzle tiles.
     */
    public List<Tile> getTiles() {
        return tiles;
    }


    /**
     * Checks whether all tiles are in the correct position.
     * @return True if all tiles are in the correct position, false otherwise.
     */
    public boolean isFinished() {
        return tiles.stream().allMatch(Tile::isInCorrectPosition);
    }


    /**
     * Utility method that tries to get a tile using its original
     * position value.
     * @param originalPos The original position of the tile.
     * @return An Optional corresponding to the tile, if present;
     *         an empty Optional otherwise.
     */
    public Tile getTileFromPos(final int originalPos) {
        return tiles.stream().filter(x -> x.getOriginalPos() == originalPos).findFirst().get();
    }


    /**
     * Factory used to create a Puzzle instance.
     * @param initialize If true, the tile structure is initialized
     *                   with as much tiles as the size of it. Otherwise,
     *                   it is initialized empty.
     * @return A fresh Puzzle instance.
     */
    public static Puzzle of(final boolean initialize) {
        return new Puzzle(initialize);
    }


    /**
     * Shortcut factory used to create a not initialized Puzzle instance
     * @return A fresh non-initialized Puzzle instance.
     */
    public static Puzzle of() {
        return new Puzzle(false);
    }
}
