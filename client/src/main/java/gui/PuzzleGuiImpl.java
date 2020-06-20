package gui;


import common.gameState.ReadableGameState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class PuzzleGuiImpl implements PuzzleGui {

    private static final int PUZZLE_WIDTH = 5;
    private static final int PUZZLE_HEIGHT = 3;
    private static final String IMAGE_PATH = "src/main/resources/bletchley-park-mansion.jpg";


    private final JFrame mainFrame;
    private final JButton joinButton;
    private final JLabel stateLabel;

    public PuzzleGuiImpl() {
        this.mainFrame = new JFrame();
        this.mainFrame.setTitle("MultiPlayer Puzzle - Client");
        this.mainFrame.setResizable(false);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        final JPanel generalPanel = new JPanel();
        generalPanel.setLayout(new BoxLayout(generalPanel, BoxLayout.PAGE_AXIS));

        this.stateLabel = new JLabel("Select join to start.");
        this.joinButton = new JButton("Join match");

        joinButton.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
               // todo
            });
        });

        generalPanel.add(generateLinePanel(joinButton, stateLabel));

        final JPanel boardPanel = new JPanel();
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
        boardPanel.setLayout(new GridLayout(PUZZLE_WIDTH, PUZZLE_HEIGHT, 0, 0));
        generalPanel.add(boardPanel, BorderLayout.CENTER);

        //createTiles(IMAGE_PATH);
        //paintPuzzle(boardPanel);

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setContentPane(generalPanel);
        mainFrame.pack();
    }


    public void launch() {
        mainFrame.setVisible(true);
    }

    @Override
    public void startMatch(ReadableGameState gameState) {

    }

    @Override
    public void rearrangeTiles(ReadableGameState gameState) {

    }

    @Override
    public void unlockInterface() {

    }

    @Override
    public void endMatch(ReadableGameState gameState) {

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

    /*

    private void createTiles(final String imagePath) {
        final BufferedImage image;

        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not load image", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        final int imageWidth = image.getWidth(null);
        final int imageHeight = image.getHeight(null);

        int position = 0;

        final List<Integer> randomPositions = new ArrayList<>();
        IntStream.range(0, PUZZLE_HEIGHT * PUZZLE_WIDTH).forEach(item -> { randomPositions.add(item); });
        //Collections.shuffle(randomPositions);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                final Image imagePortion = createImage(new FilteredImageSource(image.getSource(),
                        new CropImageFilter(j * imageWidth / columns,
                                i * imageHeight / rows,
                                (imageWidth / columns),
                                imageHeight / rows)));

                tiles.add(new Tile(imagePortion, position, randomPositions.get(position)));
                position++;
            }
        }
    }

    private void paintPuzzle(final JPanel board) {
        board.removeAll();

        Collections.sort(tiles);

        tiles.forEach(tile -> {
            final TileButton btn = new TileButton(tile);
            board.add(btn);
            btn.setBorder(BorderFactory.createLineBorder(Color.gray));
            btn.addActionListener(actionListener -> {
                selectionManager.selectTile(tile, () -> {
                    paintPuzzle(board);
                    checkSolution();
                });
            });
        });
    }

     */
}
