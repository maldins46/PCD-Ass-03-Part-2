package gui;

import common.client.GameClient;
import common.client.config.Destinations;
import common.client.messages.Messages;
import common.client.messages.SelectMsg;
import common.client.messages.SwapMsg;
import common.gameState.ClientGameState;
import common.model.Player;
import common.model.Tile;

import javax.swing.*;

import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Class that enclose concept of Tile with the functionalities of JButton.
 */
public final class TileButton extends JButton implements ActionListener {
    /**
     * The tile original position.
     */
    private final int originalPos;

    /**
     * The GameClient used for connecting.
     */
    private final GameClient client;
    /**
     * The clientGameState used for updates.
     */
    private final ClientGameState state;
    /**
     * The images in all tiles.
     */
    private final List<Image> images;
    /**
     * The graphic interface.
     */
    private final PuzzleGui gui;
    /**
     * The player that have checked this tile button.
     */
    private Player currPlayer;

    /**
     * Actually tile in this tile button.
     */
    private Tile currTile;
    /**
     * Actually image in this tile button.
     */
    private Image currImage;
    /**
     * True if the tile button is clickable, false otherwise.
     */
    private boolean clickable = false;


    /**
     * Create a tile button instance.
     * @param originalPos The original position of tile.
     * @param originalImage The original image of tile button.
     * @param images The images in all tiles.
     * @param state The clientGameState used for updates.
     * @param client The GameClient used for connecting.
     * @param gui The graphic interface.
     */
    public TileButton(final int originalPos, final Image originalImage,
                      final List<Image> images, final ClientGameState state,
                      final GameClient client, final PuzzleGui gui) {
        super(new ImageIcon(originalImage));

        this.currImage = originalImage;
        this.originalPos = originalPos;
        this.state = state;
        this.client = client;
        this.images = images;
        this.gui = gui;
        addActionListener(this);
        setBorder(BorderFactory.createLineBorder(Color.gray));

    }

    /**
     * Update the tile button parameter.
     */
    public void update() {
        this.currTile = state.getPuzzle().getTileFromPos(originalPos).get();
        this.currPlayer = Player.of(Destinations.PERSONAL_QUEUE);

        if (currTile.getSelector().equals(currPlayer)) {
            setBorder(BorderFactory.createLineBorder(Color.green));

        } else if (!currTile.getSelector().equals(Player.empty())) {
            setBorder(BorderFactory.createLineBorder(Color.red));

        } else {
           setBorder(BorderFactory.createLineBorder(Color.gray));
        }

        final Image newImage = images.get(currTile.getCurrentPos());
        if (!currImage.equals(newImage))
            setIcon(new ImageIcon(newImage));

        currImage = newImage;
    }

    /**
     * Set clickable or not a button.
     * @param clickable True if clickable, false otherwise.
     */
    public void setClickable(final boolean clickable) {
        this.clickable = clickable;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            // send msg
            if (clickable) {
                final List<Tile> tiles = state.getPuzzle().getTiles();

                // current player did not selected anything, and current tile is not selected by anyone
                if (tiles.stream().noneMatch(x -> x.getSelector().equals(currPlayer))
                        && currTile.getSelector().equals(Player.empty())) {
                    final SelectMsg msg = Messages.createSelectMsg(currTile, currPlayer);
                    client.sendMessageToServer(msg);
                    gui.lockInterface();
                }
                // current player selected something previously (not me)
                else if (tiles.stream().anyMatch(x -> x.getSelector().equals(currPlayer))
                         && !currTile.getSelector().equals(currPlayer)) {
                    // Always present a player in the tile
                    final Tile startTile = tiles.stream().filter(x -> x.getSelector().equals(currPlayer)).findFirst().get();
                    final SwapMsg msg = Messages.createSwapMsg(startTile, currTile, currPlayer);
                    client.sendMessageToServer(msg);
                    gui.lockInterface();
                }
            }
        });
    }
}
