package gui;

import common.client.GameClient;
import common.client.config.Destinations;
import common.client.messages.SelectMsg;
import common.client.messages.SwapMsg;
import common.gameState.ReadableGameState;
import common.model.Player;
import common.model.Tile;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class that enclose concept of Tile with the funzionalities of JButton.
 */
public final class TileButton extends JButton implements ActionListener {
    private final int originalPosition;

    private final GameClient client;
    private final ReadableGameState gameState;
    private final List<Image> images;
    private final PuzzleGui gui;
    private final Player currPlayer;

    private Tile currTile;
    private boolean clickable = false;


    public TileButton(final int originalPosition, final Image originalImage,
                      final List<Image> images,
                      final ReadableGameState gameState,
                      final GameClient client, final PuzzleGui gui, final Player currPlayer) {
        super(new ImageIcon(originalImage));

        this.originalPosition = originalPosition;
        this.gameState = gameState;
        this.client = client;
        this.images = images;
        this.gui = gui;
        this.currPlayer = currPlayer;
        addActionListener(this);
    }

    public void update() {
        this.currTile = gameState.getPuzzle().getTiles().get(originalPosition);

        if (currTile.getSelector().equals(currPlayer)) {
            setBorder(BorderFactory.createLineBorder(Color.green));
        } else if (!currTile.getSelector().equals(Player.generateEmpty())) {
            setBorder(BorderFactory.createLineBorder(Color.red));
        } else {
           setBorder(BorderFactory.createLineBorder(Color.gray));
        }

        setIcon(new ImageIcon(images.get(currTile.getCurrentPosition())));

        addActionListener(this);

    }

    public void setClickable(final boolean clickable) {
        this.clickable = clickable;
        this.setEnabled(clickable);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            // send msg
            if (clickable) {
                final List<Tile> tiles = gameState.getPuzzle().getTiles();

                // current player did not selected anything, and current tile is not selected by anyone
                if (tiles.stream().noneMatch(x -> x.getSelector().equals(currPlayer))
                        && currTile.getSelector().equals(Player.generateEmpty())) {
                    final SelectMsg msg = new SelectMsg(Destinations.MAIN_CLIENT_QUEUE, currTile, currPlayer);
                    client.sendMessage(msg, Destinations.SERVER_QUEUE_NAME);
                    gui.lockInterface();
                }

                // current player selected something previously (not me)
                else if (tiles.stream().anyMatch(x -> x.getSelector().equals(currPlayer))
                        && !currTile.getSelector().equals(Player.generateEmpty())) {
                    final Tile startTile = tiles.stream().filter(x -> x.getSelector().equals(currPlayer)).findFirst().get();
                    final SwapMsg msg = new SwapMsg(Destinations.MAIN_CLIENT_QUEUE, startTile,currTile, currPlayer);
                    client.sendMessage(msg, Destinations.SERVER_QUEUE_NAME);
                    gui.lockInterface();
                }
            }
        });
    }
}
