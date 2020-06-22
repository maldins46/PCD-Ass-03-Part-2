package common.model;

import java.util.ArrayList;
import java.util.List;

public final class Puzzle {
    public static final int PUZZLE_WIDTH = 5;
    public static final int PUZZLE_HEIGHT = 3;


    /**
     * The number of tiles in width in the puzzle.
     */
    private final int width;

    /**
     * The number of tiles in height in the puzzle.
     */
    private final int height;

    /**
     * The number of tiles. It's find with height * width.
     */
    private final int size;

    /**
     * The list of tiles.
     */
    private final List<Tile> tiles;


    /**
     * Constructor that initialize the dimensions of the puzzle.
     */
    public Puzzle() {
        this.width = PUZZLE_WIDTH;
        this.height = PUZZLE_HEIGHT;
        this.size = width * height;
        this.tiles = new ArrayList<>();
    }


    /**
     * Getter for width. Unused except for marshalling.
     * @return The puzzle's width.
     */
    public int getWidth() {
        return width;
    }


    /**
     * Getter for height. Unused except for marshalling.
     * @return The puzzle's height.
     */
    public int getHeight() {
        return height;
    }


    /**
     * Getter for size.
     * @return The puzzle's size.
     */
    public int getSize() {
        return size;
    }


    /**
     * Getter for all the puzzle's tiles.
     * @return The puzzle tiles.
     */
    public List<Tile> getTiles() {
        return tiles;
    }
}
