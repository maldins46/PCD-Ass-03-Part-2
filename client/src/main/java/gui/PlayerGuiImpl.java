package gui;


import common.amqp.client.PlayerClient;
import common.amqp.messages.Messages;
import common.amqp.messages.NewPlayerMsg;
import common.amqp.messages.RematchMsg;
import common.gameState.PlayerGameState;

import javax.swing.*;
import java.awt.*;

final class PlayerGuiImpl implements PlayerGui {

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

    private final PuzzlePanel puzzlePanel;


    /**
     * Creates the graphic interface but actually is not visible.
     * @param state The clientGameState that update the puzzle data.
     * @param client The AMQP client that do the network work.
     */
    PlayerGuiImpl(final PlayerGameState state, final PlayerClient client) {

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
                final NewPlayerMsg msg = Messages.createNewPlayerMsg(state.getCurrentPlayer());
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
        puzzlePanel.enablePuzzle();
        puzzlePanel.setSwappable();

        stateLabel.setText("Playing match...");
        joinButton.setEnabled(false);
        rematchButton.setEnabled(false);
    }


    @Override
    public void setPuzzleSelectable() {
        puzzlePanel.enablePuzzle();
        puzzlePanel.setSelectable();

        stateLabel.setText("Playing match...");
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
