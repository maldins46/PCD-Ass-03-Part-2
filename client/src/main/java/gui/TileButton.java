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
 * Represents a single tile of the puzzle, shown as a clickable button
 * to the user.
 */
final class TileButton extends JButton {

    /**
     * Actual tile associated to the button.
     */
    private Tile tile;

    /**
     * A tile is locked if a message is sent from the player, and
     * the player is waiting for the ack. A locked tile can be clicked,
     * but the click won't take effect.
     */
    private boolean locked;

    /**
     * Image currently displayed over this tile.
     */
    private Image currImage;

    /**
     * A refer to the player client, used to send messages.
     */
    private final PlayerClient client;

    /**
     * A refer to the game state, to access some useful game information.
     */
    private final PlayerGameState gameState;

    /**
     * A refer to the panel that generated the tile.
     */
    private final PuzzlePanel puzzlePanel;


    /**
     * Default constructor with field population.
     * @param tile Actual tile associated to the button.
     * @param originalImage Image firstly displayed over this tile.
     * @param gameState A refer to the game state, to access some useful game information.
     * @param client A refer to the player client, used to send messages.
     * @param puzzlePanel A refer to the panel that generated the tile.
     */
    TileButton(final Tile tile, final Image originalImage, final PlayerGameState gameState,
               final PlayerClient client, final PuzzlePanel puzzlePanel) {
        super(new ImageIcon(originalImage));
        setBorder(BorderFactory.createLineBorder(Color.gray));
        setEnabled(false);

        this.currImage = originalImage;
        this.tile = tile;
        this.gameState = gameState;
        this.client = client;
        this.puzzlePanel = puzzlePanel;
    }


    /**
     * Makes the tile swappable, as described in the GUI
     * interface.
     */
    public void setSwappable() {
        removeActionListener(selectableActionListener());
        addActionListener(swappableActionListener());
    }


    /**
     * Makes the tile selectable, as described in the GUI
     * interface.
     */
    public void setSelectable() {
        removeActionListener(swappableActionListener());
        addActionListener(selectableActionListener());
    }


    /**
     * Makes the tile locked, as described in the GUI
     * interface.
     */
    public void setLocked(final boolean locked) {
        this.locked = locked;
    }


    /**
     * Updates how the tile is shown, including images and select borders.
     */
    public void updateAppearance(final Tile tile, final Image newImage) {
        this.tile = tile;
        if (!currImage.equals(newImage)) {
            setIcon(new ImageIcon(newImage));
        }

        currImage = newImage;

        if (tile.getSelector().equals(gameState.getCurrentPlayer())) {
            setBorder(BorderFactory.createLineBorder(Color.green));

        } else if (!tile.getSelector().equals(Player.empty())) {
            setBorder(BorderFactory.createLineBorder(Color.red));

        } else {
            setBorder(BorderFactory.createLineBorder(Color.gray));
        }
    }


    /**
     * Action listener associated to the swap behavior.
     * @return The associated listener.
     */
    private ActionListener swappableActionListener() {
        return e -> SwingUtilities.invokeLater(() -> {
            if (!locked && !tile.getSelector().equals(gameState.getCurrentPlayer())) {
                final Tile startTile = gameState.getSelectedTile();
                final SwapMsg msg = Messages.createSwapMsg(startTile, tile);
                client.sendMessageToPuzzleService(msg);
                puzzlePanel.lockPuzzle();
            }
        });
    }


    /**
     * Action listener associated to the select behavior.
     * @return The associated listener.
     */
    private ActionListener selectableActionListener() {
        return e -> SwingUtilities.invokeLater(() -> {
            if (!locked && tile.getSelector().equals(Player.empty())) {
                final SelectMsg msg = Messages.createSelectMsg(tile);
                client.sendMessageToPuzzleService(msg);
                puzzlePanel.lockPuzzle();
            }
        });
    }
}
