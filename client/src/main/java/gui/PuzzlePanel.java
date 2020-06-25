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

/**
 * Represents the panel that contains the tiles of the puzzle.
 */
final class PuzzlePanel extends JPanel {
    /**
     * The path of the image used as 'wallpaper' in the puzzle. It can be
     * changed easily with any image of the same dimension.
     */
    private static final String IMAGE_PATH = "src/main/resources/bletchley-park-mansion.jpg";

    /**
     * A refer to the player client, used to send messages.
     */
    private final PlayerClient client;

    /**
     * A refer to the game state, to access some useful game information.
     */
    private final PlayerGameState gameState;

    /**
     * Keeps track of all tile buttons of the puzzle.
     */
    private final List<TileButton> tileButtons = new ArrayList<>();

    /**
     * Keeps track of the images in which the puzzle has been divided.
     */
    private final List<Image> tileImages = new ArrayList<>();


    /**
     * Default constructor with field population.
     * @param client The client instance.
     * @param gameState The game state instance.
     */
    PuzzlePanel(final PlayerClient client, final PlayerGameState gameState) {
        this.client = client;
        this.gameState = gameState;

        setBorder(BorderFactory.createLineBorder(Color.gray));
        setLayout(new GridLayout(Puzzle.HEIGHT, Puzzle.WIDTH, 0, 0));
        createTiles();
    }


    /**
     * Makes all tiles of the puzzle swappable, as described in the GUI
     * interface.
     */
    void setSwappable() {
        for (TileButton tileButton : tileButtons) {
            tileButton.setSwappable();
        }
    }


    /**
     * Makes all tiles of the puzzle selectable, as described in the GUI
     * interface.
     */
    void setSelectable() {
        for (TileButton tileButton : tileButtons) {
            tileButton.setSelectable();
        }
    }


    /**
     * Unlocks all tiles of the puzzle, following an ack messsage.
     */
    void unlockPuzzle() {
        for (TileButton tileButton : tileButtons) {
            tileButton.setLocked(false);
        }
    }


    /**
     * Locks all tiles of the puzzle, following a sent message.
     */
    void lockPuzzle() {
        for (TileButton tileButton : tileButtons) {
            tileButton.setLocked(true);
        }
    }


    /**
     * Enables all tiles of the puzzle.
     */
    void enablePuzzle() {
        for (TileButton tileButton : tileButtons) {
            tileButton.setEnabled(true);
        }
    }


    /**
     * Disables all tiles of the puzzle.
     */
    void disablePuzzle() {
        for (TileButton tileButton : tileButtons) {
            tileButton.setEnabled(false);
        }
    }


    /**
     * Updates how tiles are shown, including images and select borders.
     */
    void updateAppearance() {
        for (int i = 0; i < tileButtons.size(); i++) {
            final Tile updatedTile = gameState.getPuzzle().getTileFromPos(i);
            final Image updatedImage = tileImages.get(updatedTile.getCurrentPos());
            tileButtons.get(i).updateAppearance(updatedTile, updatedImage);
        }
    }


    /**
     * Divides the images in sub-images associated with tiles, and generates
     * the basic tile button structure.
     */
    private void createTiles() {
        final BufferedImage image;

        try {
            image = ImageIO.read(new File(IMAGE_PATH));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Could not load image", "Error", JOptionPane.ERROR_MESSAGE);
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
            TileButton tileButton = new TileButton(Tile.of(i), tileImages.get(i), gameState, client, this);
            tileButtons.add(tileButton);
            add(tileButton);
        }
    }
}
