package gui;

import common.amqp.client.PlayerClient;
import common.gameState.PlayerGameState;
import common.model.Puzzle;
import common.model.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PuzzlePanel extends JPanel {
    /**
     * The image path.
     */
    private static final String IMAGE_PATH = "src/main/resources/bletchley-park-mansion.jpg";



    private final PlayerClient client;
    private final PlayerGameState state;

    private final List<TileButton> tileButtons = new ArrayList<>();
    private final List<Image> tileImages = new ArrayList<>();


    public PuzzlePanel(final PlayerClient client, final PlayerGameState state) {
        this.client = client;
        this.state = state;

        setBorder(BorderFactory.createLineBorder(Color.gray));
        setLayout(new GridLayout(Puzzle.HEIGHT, Puzzle.WIDTH, 0, 0));

        createTiles();
    }

    void setSwappable() {
        for (int i = 0; i < tileButtons.size(); i++) {
            final Optional<Tile> updatedTileOpt = state.getPuzzle().getTileFromPos(i);

            if (updatedTileOpt.isPresent()) {
                final Tile updatedTile = updatedTileOpt.get();
                tileButtons.get(i).setSwappable(updatedTile, tileImages.get(updatedTile.getCurrentPos()));
            }
        }
    }


    void setSelectable() {
        for (int i = 0; i < tileButtons.size(); i++) {
            final Optional<Tile> updatedTileOpt = state.getPuzzle().getTileFromPos(i);

            if (updatedTileOpt.isPresent()) {
                final Tile updatedTile = updatedTileOpt.get();
                tileButtons.get(i).setSelectable(updatedTile, tileImages.get(updatedTile.getCurrentPos()));
            }
        }
    }


    void unlockPuzzle() {
        for (TileButton tileButton : tileButtons) {
            tileButton.setLocked(false);
        }
    }


    void lockPuzzle() {
        for (TileButton tileButton : tileButtons) {
            tileButton.setLocked(true);
        }
    }


    void enablePuzzle() {
        for (int i = 0; i < tileButtons.size(); i++) {
            final Optional<Tile> updatedTileOpt = state.getPuzzle().getTileFromPos(i);

            if (updatedTileOpt.isPresent()) {
                final Tile updatedTile = updatedTileOpt.get();
                tileButtons.get(i).setEnabled(true, updatedTile, tileImages.get(updatedTile.getCurrentPos()));
            }
        }
    }


    void disablePuzzle() {
        for (int i = 0; i < tileButtons.size(); i++) {
            final Optional<Tile> updatedTileOpt = state.getPuzzle().getTileFromPos(i);

            if (updatedTileOpt.isPresent()) {
                final Tile updatedTile = updatedTileOpt.get();
                tileButtons.get(i).setEnabled(false, updatedTile, tileImages.get(updatedTile.getCurrentPos()));
            }
        }
    }


    private void createTiles() {
        final BufferedImage image;

        try {
            image = ImageIO.read(new File(IMAGE_PATH));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not load image", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        final int imageWidth = image.getWidth(null);
        final int imageHeight = image.getHeight(null);

        for (int i = 0; i < Puzzle.HEIGHT; i++) {
            for (int j = 0; j < Puzzle.WIDTH; j++) {
                final Image imagePortion =
                        createImage(new FilteredImageSource(image.getSource(),
                                new CropImageFilter(j * imageWidth / Puzzle.WIDTH,
                                        i * imageHeight / Puzzle.HEIGHT,
                                        (imageWidth / Puzzle.WIDTH),
                                        imageHeight / Puzzle.HEIGHT)));

                tileImages.add(imagePortion);
            }
        }

        for (int i = 0; i < tileImages.size(); i++) {
            TileButton tileButton = new TileButton(Tile.of(i), tileImages.get(i), state, client, this);
            tileButtons.add(tileButton);
            add(tileButton);
        }
    }
}
