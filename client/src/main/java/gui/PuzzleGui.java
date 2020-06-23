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

    void lockInterface();


    /**
     * Factory for create an implementation of this interface.
     * @return The implementation of this interface.
     */
    static PuzzleGui of(GameClient client, ClientGameState gameState) {
        return new PuzzleGuiImpl(gameState, client);
    }
}
