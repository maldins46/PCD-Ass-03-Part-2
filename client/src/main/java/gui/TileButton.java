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
 * Class that enclose concept of Tile with the funzionalities of JButton.
 */
public final class TileButton extends JButton implements ActionListener {
    private final int originalPos;

    private final GameClient client;
    private final ClientGameState state;
    private final List<Image> images;
    private final PuzzleGui gui;
    private Player currPlayer;

    private Tile currTile;
    private Image currImage;
    private boolean clickable = false;


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
                    final SwapMsg msg = Messages.createSwapMsg(startTile,currTile, currPlayer);
                    client.sendMessageToServer(msg);
                    gui.lockInterface();
                }
            }
        });
    }
}
