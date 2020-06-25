package gui;

import common.amqp.client.PlayerClient;
import common.amqp.messages.Messages;
import common.amqp.messages.SelectMsg;
import common.amqp.messages.SwapMsg;
import common.gameState.PlayerGameState;
import common.model.Player;
import common.model.Tile;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class that enclose concept of Tile with the functionalities of JButton.
 */
public final class TileButton extends JButton {

    /**
     * Actually tile in this tile button.
     */
    private Tile tile;

    /**
     *
     */
    private boolean locked;

    /**
     * Actually image in this tile button.
     */
    private Image currImage;

    /**
     * The GameClient used for connecting.
     */
    private final PlayerClient client;

    /**
     * The clientGameState used for updates.
     */
    private final PlayerGameState state;

    private final PuzzlePanel puzzlePanel;


    /**
     * Create a tile button instance.
     * @param originalImage The original image of tile button.
     * @param state The clientGameState used for updates.
     * @param client The GameClient used for connecting.
     */
    public TileButton(final Tile tile, final Image originalImage,
                      final PlayerGameState state,
                      final PlayerClient client,
                      final PuzzlePanel puzzlePanel) {
        super(new ImageIcon(originalImage));

        this.currImage = originalImage;
        this.tile = tile;
        this.state = state;
        this.client = client;
        this.puzzlePanel = puzzlePanel;

        setBorder(BorderFactory.createLineBorder(Color.gray));
        setEnabled(false);
    }


    public void setSwappable(final Tile tile, final Image newImage) {
        this.tile = tile;
        updateAppearance(tile.getSelector(), newImage);
        removeActionListener(selectableActionListener());
        addActionListener(swappableActionListener());
    }


    public void setSelectable(final Tile tile, final Image newImage) {
        this.tile = tile;
        updateAppearance(tile.getSelector(), newImage);
        removeActionListener(swappableActionListener());
        addActionListener(selectableActionListener());
    }


    private void updateAppearance(final Player selector, final Image newImage) {
        if (!currImage.equals(newImage)) {
            setIcon(new ImageIcon(newImage));
        }

        currImage = newImage;

        if (selector.equals(state.getCurrentPlayer())) {
            setBorder(BorderFactory.createLineBorder(Color.green));

        } else if (!selector.equals(Player.empty())) {
            setBorder(BorderFactory.createLineBorder(Color.red));

        } else {
            setBorder(BorderFactory.createLineBorder(Color.gray));
        }
    }


    private ActionListener swappableActionListener() {
        return e -> SwingUtilities.invokeLater(() -> {
            if (!locked && !tile.getSelector().equals(state.getCurrentPlayer())) {
                final Tile startTile = state.getSelectedTile();
                final SwapMsg msg = Messages.createSwapMsg(startTile, tile, state.getCurrentPlayer());
                client.sendMessageToPuzzleService(msg);
                puzzlePanel.lockPuzzle();
            }
        });
    }


    private ActionListener selectableActionListener() {
        return e -> SwingUtilities.invokeLater(() -> {
            if (!locked && tile.getSelector().equals(Player.empty())) {
                final SelectMsg msg = Messages.createSelectMsg(tile, state.getCurrentPlayer());
                client.sendMessageToPuzzleService(msg);
                puzzlePanel.lockPuzzle();
            }
        });
    }


    public void setEnabled(boolean enabled, final Tile tile, final Image newImage) {
        this.tile = tile;
        updateAppearance(tile.getSelector(), newImage);
        super.setEnabled(enabled);
    }


    public void setLocked(final boolean locked) {
        this.locked = locked;
    }
}
