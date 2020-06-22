package gui;

import common.client.GameClient;
import common.gameState.ReadableGameState;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Class that enclose concept of Tile with the funzionalities of JButton.
 */
public final class TileButton extends JButton {
    private final Image originalImage;
    private int originalPosition;
    private boolean clickable = false;

    private final GameClient client;
    private final ReadableGameState gameState;
    private final List<Image> images;
    private final PuzzleGui gui;

    public TileButton(final int originalPosition, final Image originalImage,
                      final List<Image> images,
                      final ReadableGameState gameState,
                      final GameClient client, final PuzzleGui gui) {
        this.originalImage = originalImage;
        this.originalPosition = originalPosition;
        this.gameState = gameState;
        this.client = client;
        this.images = images;
        this.gui = gui;
        // todo
    }

    public void update() {
        // todo
        // update

    }

    public void setClickable(final boolean clickable) {
        // todo
    }

    @Override
    public void addActionListener(final ActionListener l) {
        SwingUtilities.invokeLater(() -> {
            // todo
            // send msg
            gui.lockInterface();
        });
    }
}
