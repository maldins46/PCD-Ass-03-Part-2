package gui;


import common.client.GameClient;
import common.client.config.Destinations;
import common.client.messages.Messages;
import common.client.messages.NewPlayerMsg;
import common.client.messages.RematchMsg;
import common.gameState.ReadableGameState;
import common.model.Player;

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

public final class PuzzleGuiImpl implements PuzzleGui {

    private static final int PUZZLE_WIDTH = 5;
    private static final int PUZZLE_HEIGHT = 3;
    private static final String IMAGE_PATH = "src/main/resources/bletchley-park-mansion.jpg";


    private final JFrame mainFrame;
    private final JButton joinButton;
    private final JButton rematchButton;
    private final JLabel stateLabel;
    private final List<TileButton> tileButtons = new ArrayList<>();
    private final List<Image> tileImages = new ArrayList<>();

    private final ReadableGameState gameState;
    private final GameClient client;
    private Player currPlayer;

    /**
     * Creates the graphic interface but actually is not visible.
     */
    PuzzleGuiImpl(final ReadableGameState gameState, final GameClient client) {
        this.gameState = gameState;
        this.client = client;

        this.mainFrame = new JFrame();
        this.mainFrame.setTitle("MultiPlayer Puzzle - Client");
        this.mainFrame.setResizable(false);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        final JPanel generalPanel = new JPanel();
        generalPanel.setLayout(new BoxLayout(generalPanel, BoxLayout.PAGE_AXIS));

        this.stateLabel = new JLabel("Select join to start.");
        this.joinButton = new JButton("Join match");
        this.rematchButton = new JButton("Rematch");
        this.rematchButton.setEnabled(false);

        joinButton.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                stateLabel.setText("Connecting to the broker...");
                joinButton.setEnabled(false);
                NewPlayerMsg msg = Messages.createNewPlayerMsg(Destinations.MAIN_CLIENT_QUEUE,
                        Player.of(Destinations.MAIN_CLIENT_QUEUE));
                client.sendMessage(msg, Destinations.SERVER_QUEUE_NAME);
            });
        });

        rematchButton.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                stateLabel.setText("Starting new match...");
                rematchButton.setEnabled(false);
                RematchMsg msg = Messages.createRematchMsg(Destinations.MAIN_CLIENT_QUEUE);
                client.sendMessage(msg, Destinations.SERVER_QUEUE_NAME);
            });
        });

        generalPanel.add(generateLinePanel(joinButton, rematchButton, stateLabel));

        final JPanel boardPanel = new JPanel();
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
        boardPanel.setLayout(new GridLayout(PUZZLE_HEIGHT, PUZZLE_WIDTH, 0, 0));

        createTiles(boardPanel);

        generalPanel.add(boardPanel);

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setContentPane(generalPanel);
        mainFrame.pack();
    }

    @Override
    public void launch() {
        mainFrame.setVisible(true);
    }

    @Override
    public void rearrangeTiles() {
        this.currPlayer = Player.of(Destinations.MAIN_CLIENT_QUEUE);
        if (gameState.getPlayers().stream().noneMatch(x -> x.equals(currPlayer))) {
            stateLabel.setText("Timeout expired! You are out of match!");
            joinButton.setEnabled(true);
            tileButtons.forEach(btn -> {
                btn.setClickable(false);
            });
        } else {
            stateLabel.setText("Match started!");
            tileButtons.forEach(btn -> {
                btn.setClickable(true);
                btn.update();
            });
        }
    }

    @Override
    public void unlockInterface() {
        tileButtons.forEach(btn -> {
            btn.setClickable(true);
        });
    }

    @Override
    public void lockInterface() {
        tileButtons.forEach(btn -> {
            btn.setClickable(false);
        });
    }

    @Override
    public void endMatch() {
        stateLabel.setText("Match finished!");
        rematchButton.setEnabled(true);
    }

    /**
     * Generates a JPanel that has n elements in line.
     * @param components the components from left to right.
     * @return the formatted panel.
     */
    private JPanel generateLinePanel(final JComponent... components) {
        final JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        for (JComponent component : components) {
            newPanel.add(component);
        }

        return newPanel;
    }


    private void createTiles(JPanel boardPanel) {
        final BufferedImage image;

        try {
            image = ImageIO.read(new File(PuzzleGuiImpl.IMAGE_PATH));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Could not load image", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        final int imageWidth = image.getWidth(null);
        final int imageHeight = image.getHeight(null);


        for (int i = 0; i < PUZZLE_HEIGHT; i++) {
            for (int j = 0; j < PUZZLE_WIDTH; j++) {
                final Image imagePortion =
                        mainFrame.createImage(new FilteredImageSource(image.getSource(),
                        new CropImageFilter(j * imageWidth / PUZZLE_WIDTH,
                                i * imageHeight / PUZZLE_HEIGHT,
                                (imageWidth / PUZZLE_WIDTH),
                                imageHeight / PUZZLE_HEIGHT)));

                tileImages.add(imagePortion);
            }
        }

        for (int i = 0; i < tileImages.size(); i++) {
            TileButton tileButton = new TileButton(i, tileImages.get(i), tileImages, gameState, client, this, currPlayer);
            tileButtons.add(tileButton);
            boardPanel.add(tileButton);
            tileButton.setClickable(false);
        }
    }
}
