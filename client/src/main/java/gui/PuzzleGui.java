package gui;

import common.client.GameClient;
import common.gameState.ClientGameState;

public interface PuzzleGui {

    /**
     * Start the visual graphic.
     */
    void launch();


    /**
     * It's shuffle all tiles for start a new match.
     */
    void rearrangeTiles();


    /**
     * Interface is locked when a player do something on it. This method
     * unlock all possible clicks that a player want to do.
     */
    void unlockInterface();

    /**
     * This method lock interface. It's used when a player do something on it.
     */
    void lockInterface();


    /**
     * Factory for create an implementation of this interface.
     * @param client The GameClient in the game.
     * @param gameState The GameState of the game.
     * @return The implementation of this interface.
     */
    static PuzzleGui of(GameClient client, ClientGameState gameState) {
        return new PuzzleGuiImpl(gameState, client);
    }
}
