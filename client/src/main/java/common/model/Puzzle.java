package common.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Puzzle {
    public static final int WIDTH = 5;
    public static final int HEIGHT = 3;
    public static final int SIZE = HEIGHT * WIDTH;


    /**
     * The list of tiles.
     */
    private final List<Tile> tiles;


    /**
     * Constructor that initialize the dimensions of the puzzle.
     * @param initializeTiles todo
     */
    private Puzzle(final boolean initializeTiles) {
        this.tiles = new ArrayList<>();

        if (initializeTiles) {
            for (int i = 0; i < Puzzle.SIZE; i++) {
                final Tile tile = new Tile(i);
                this.tiles.add(tile);
            }
        }
    }


    /**
     * Getter for all the puzzle's tiles.
     * @return The puzzle tiles.
     */
    public List<Tile> getTiles() {
        return tiles;
    }


    public Optional<Tile> getTileFromPos(final int originalPos) {
        return tiles.stream().filter(x -> x.getOriginalPos() == originalPos).findFirst();
    }

    public static Puzzle of(final boolean initialize) {
        return new Puzzle(initialize);
    }

    public static Puzzle of() {
        return new Puzzle(false);
    }
}
