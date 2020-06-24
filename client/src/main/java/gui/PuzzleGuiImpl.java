package gui;


import common.client.GameClient;
import common.client.config.Destinations;
import common.client.messages.Messages;
import common.client.messages.NewPlayerMsg;
import common.client.messages.RematchMsg;
import common.gameState.ClientGameState;
import common.model.Player;
import common.model.Puzzle;

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

    /**
     * The image path.
     */
    private static final String IMAGE_PATH = "src/main/resources/bletchley-park-mansion.jpg";


    private final JFrame mainFrame;

    /**
     * It's used for play in the match.
     */
    private final JButton joinButton;

    /**
     * It's used for start a new match.
     */
    private final JButton rematchButton;


    private final JLabel stateLabel;
    private final List<TileButton> tileButtons = new ArrayList<>();
    private final List<Image> tileImages = new ArrayList<>();

    /**
     * The gameClientState.
     */
    private final ClientGameState state;
    private final GameClient client;

    /**
     * The player that play at this interface instance.
     */
    private Player currPlayer;

    /**
     * Creates the graphic interface but actually is not visible.
     * @param state The clientGameState that update the puzzle data.
     * @param client The AMQP client that do the network work.
     */
    PuzzleGuiImpl(final ClientGameState state, final GameClient client) {
        this.state = state;
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
                final NewPlayerMsg msg = Messages.createNewPlayerMsg(currPlayer);
                client.sendMessageToServer(msg);
            });
        });

        rematchButton.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                stateLabel.setText("Starting new match...");
                rematchButton.setEnabled(false);
                final RematchMsg msg = Messages.createRematchMsg();
                client.sendMessageToServer(msg);
            });
        });

        generalPanel.add(generateLinePanel(joinButton, rematchButton, stateLabel));

        final JPanel boardPanel = new JPanel();
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
        boardPanel.setLayout(new GridLayout(Puzzle.HEIGHT, Puzzle.WIDTH, 0, 0));

        createTiles(boardPanel);

        generalPanel.add(boardPanel);

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setContentPane(generalPanel);
        mainFrame.pack();
    }

    @Override
    public void launch() {
        mainFrame.setVisible(true);
        currPlayer = Player.of(Destinations.PERSONAL_QUEUE);
    }

    @Override
    public void rearrangeTiles() {
        if (state.getPlayers().stream().noneMatch(x -> x.equals(currPlayer))) {
            stateLabel.setText("You are out of match!");
            joinButton.setEnabled(true);
            rematchButton.setEnabled(false);
            tileButtons.forEach(btn -> btn.setClickable(false));

        } else if (state.getWin()) {
            stateLabel.setText("You win! Match finished!");
            joinButton.setEnabled(false);
            rematchButton.setEnabled(true);
            tileButtons.forEach(TileButton::update);
            tileButtons.forEach(btn -> btn.setClickable(false));

        } else {
            stateLabel.setText("Match started!");
            joinButton.setEnabled(false);
            rematchButton.setEnabled(false);
            tileButtons.forEach(TileButton::update);
        }
    }

    @Override
    public void unlockInterface() {
        tileButtons.forEach(btn -> btn.setClickable(true));
    }

    @Override
    public void lockInterface() {
        tileButtons.forEach(btn -> btn.setClickable(false));
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


        for (int i = 0; i < Puzzle.HEIGHT; i++) {
            for (int j = 0; j < Puzzle.WIDTH; j++) {
                final Image imagePortion =
                        mainFrame.createImage(new FilteredImageSource(image.getSource(),
                        new CropImageFilter(j * imageWidth / Puzzle.WIDTH,
                                i * imageHeight / Puzzle.HEIGHT,
                                (imageWidth / Puzzle.WIDTH),
                                imageHeight / Puzzle.HEIGHT)));

                tileImages.add(imagePortion);
            }
        }

        for (int i = 0; i < tileImages.size(); i++) {
            TileButton tileButton = new TileButton(i, tileImages.get(i), tileImages, state, client, this);
            tileButtons.add(tileButton);
            boardPanel.add(tileButton);
            tileButton.setClickable(false);
        }
    }
}
