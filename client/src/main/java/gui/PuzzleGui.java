package gui;

import common.gameState.ReadableGameState;

public interface PuzzleGui {

    /**
     * Start the visual graphic.
     */
    void launch();


    /**
     * It's start the game with new data. It's used at the start of all match.
     * @param gameState The entire tiles of the puzzle. This tiles are only
     *                  readable because only server do the swap of two tiles.
     */
    void startMatch(ReadableGameState gameState);


    /**
     * It's shuffle all tiles for start a new match.
     * @param gameState The entire tiles of the puzzle. This tiles are only
     *                  readable because only server do the swap of two tiles.
     */
    void rearrangeTiles(ReadableGameState gameState);


    /**
     * Interface is locked when a player do something on it. This method
     * unlock all possible clicks that a player want to do.
     */
    void unlockInterface();


    /**
     * It's called when the match is finished. Terminate the game and the Gui
     * became not interactive.
     * @param gameState The entire tiles of the puzzle. This tiles are only
     *                  readable because only server do the swap of two tiles.
     */
    void endMatch(ReadableGameState gameState);

    /**
     * Factory for create an implementation of this interface.
     * @return The implementation of this interface.
     */
    static PuzzleGui of() {
        return new PuzzleGuiImpl();
    }
}
