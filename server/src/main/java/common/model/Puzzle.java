package common.model;

import java.util.ArrayList;
import java.util.List;

public final class Puzzle {
    private int width;
    private int height;
    private int size;
    private List<Tile> tiles;

    public Puzzle(final int width, final int height) {
        this.width = width;
        this.height = height;
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
