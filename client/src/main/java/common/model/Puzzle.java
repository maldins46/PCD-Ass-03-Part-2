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

    public Puzzle() {
        this.width = PUZZLE_WIDTH;
        this.height = PUZZLE_HEIGHT;
        this.size = width * height;
        this.tiles = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSize() {
        return size;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}
