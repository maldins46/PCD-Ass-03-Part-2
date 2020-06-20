package common.model;

import java.util.ArrayList;
import java.util.List;

public final class Puzzle {
    public static final int PUZZLE_WIDTH = 5;
    public static final int PUZZLE_HEIGHT = 3;

    private int width;
    private int height;
    private int size;
    private List<Tile> tiles;

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
