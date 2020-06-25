package gui;

import common.amqp.client.PlayerClient;
import common.gameState.PlayerGameState;

public interface GameGui {

    /**
     * Start the visual graphic.
     */
    void launch();

    void finishMatch();

    void leaveGame();

    void setPuzzleSwappable();

    void setPuzzleSelectable();

    void unlockPuzzle();


    /**
     * Factory for create an implementation of this interface.
     * @param client The GameClient in the game.
     * @param gameState The GameState of the game.
     * @return The implementation of this interface.
     */
    static GameGui of(PlayerClient client, PlayerGameState gameState) {
        return new GameGuiImpl(gameState, client);
    }
}
