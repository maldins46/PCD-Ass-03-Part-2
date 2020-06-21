package gui;

import common.client.GameClient;
import common.gameState.ReadableGameState;

public interface PuzzleGui {

    /**
     * Start the visual graphic.
     */
    void launch();


    /**
     * It's start the game with new data. It's used at the start of all match.
     */
    void startMatch();


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
     * It's called when the match is finished. Terminate the game and the Gui
     * became not interactive.
     */
    void endMatch();

    /**
     * Factory for create an implementation of this interface.
     * @return The implementation of this interface.
     */
    static PuzzleGui of(GameClient client, ReadableGameState gameState) {
        return new PuzzleGuiImpl(gameState, client);
    }
}
