package gui;


import common.amqp.client.PlayerClient;
import common.amqp.messages.Messages;
import common.amqp.messages.NewPlayerMsg;
import common.amqp.messages.RematchMsg;
import common.gameState.PlayerGameState;

import javax.swing.*;
import java.awt.*;

/**
 * Implementation of the access point of modification for the GUI.
 */
final class PlayerGuiImpl implements PlayerGui {

    /**
     * The frame shown to the user.
     */
    private final JFrame mainFrame;

    /**
     * Used to send to the puzzle service the initial game join request.
     */
    private final JButton joinButton;

    /**
     * Used to send to the puzzle service a request to start a new game.
     */
    private final JButton rematchButton;

    /**
     * A label that shows some information about game state.
     */
    private final JLabel stateLabel;

    /**
     * The panel that shows all the tiles of the puzzle.
     */
    private final PuzzlePanel puzzlePanel;

    /**
     * A refer to the game state, to access some useful game information.
     */
    private final PlayerGameState gameState;


    /**
     * Creates the GUI, keeping it not visible until the launch.
     * @param state The game state instance.
     * @param client The client instance.
     */
    PlayerGuiImpl(final PlayerGameState state, final PlayerClient client) {
        this.mainFrame = new JFrame();
        this.mainFrame.setTitle("MultiPlayer Puzzle - Client");
        this.mainFrame.setResizable(false);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameState = state;

        final JPanel generalPanel = new JPanel();
        generalPanel.setLayout(new BoxLayout(generalPanel, BoxLayout.PAGE_AXIS));

        this.stateLabel = new JLabel("Select join to start.");
        this.joinButton = new JButton("Join match");
        this.rematchButton = new JButton("Rematch");
        this.rematchButton.setEnabled(false);

        joinButton.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                stateLabel.setText("Connecting to the game...");
                joinButton.setEnabled(false);
                final NewPlayerMsg msg = Messages.createNewPlayerMsg();
                client.sendMessageToPuzzleService(msg);
            });
        });

        rematchButton.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                stateLabel.setText("Starting new match...");
                rematchButton.setEnabled(false);
                final RematchMsg msg = Messages.createRematchMsg();
                client.sendMessageToPuzzleService(msg);
            });
        });

        generalPanel.add(generateLinePanel(joinButton, rematchButton, stateLabel));

        puzzlePanel = new PuzzlePanel(client, state);
        generalPanel.add(puzzlePanel);

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setContentPane(generalPanel);
        mainFrame.pack();
    }


    @Override
    public void launch() {
        mainFrame.setVisible(true);
    }


    @Override
    public void finishMatch() {
        puzzlePanel.updateAppearance();
        puzzlePanel.disablePuzzle();
        stateLabel.setText("Match finished!");
        joinButton.setEnabled(false);
        rematchButton.setEnabled(true);
    }


    @Override
    public void leaveGame() {
        puzzlePanel.disablePuzzle();
        stateLabel.setText("You are out! Select join to start.");
        joinButton.setEnabled(true);
        rematchButton.setEnabled(false);
    }


    @Override
    public void setPuzzleSwappable() {
        puzzlePanel.updateAppearance();
        puzzlePanel.enablePuzzle();
        puzzlePanel.setSwappable();
        stateLabel.setText("Playing match, " + gameState.getPlayers().size() + " players connected");
        joinButton.setEnabled(false);
        rematchButton.setEnabled(false);
    }


    @Override
    public void setPuzzleSelectable() {
        puzzlePanel.updateAppearance();
        puzzlePanel.enablePuzzle();
        puzzlePanel.setSelectable();

        stateLabel.setText("Playing match, " + gameState.getPlayers().size() + " players connected");
        joinButton.setEnabled(false);
        rematchButton.setEnabled(false);
    }


    @Override
    public void unlockPuzzle() {
        puzzlePanel.unlockPuzzle();
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
}
