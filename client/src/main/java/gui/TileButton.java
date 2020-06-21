package gui;

import common.client.GameClient;
import common.gameState.ReadableGameState;
import common.model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class TileButton extends JButton {
    private final Image originalImage;
    private int originalPosition;
    private boolean clickable = false;

    private final GameClient client;
    private final ReadableGameState gameState;
    private final List<Image> images;
    private final PuzzleGui gui;

    public TileButton(int originalPosition, Image originalImage, List<Image> images, ReadableGameState gameState, GameClient client, PuzzleGui gui) {
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
    public void addActionListener(ActionListener l) {
        SwingUtilities.invokeLater(() -> {
            // todo
            // send msg
            gui.lockInterface();
        });
    }
}
