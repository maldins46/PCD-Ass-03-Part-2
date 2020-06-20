package gui;

import common.gameState.ReadableGameState;

public interface PuzzleGui {
    void launch();
    void startMatch(ReadableGameState gameState);
    void rearrangeTiles(ReadableGameState gameState);
    void unlockInterface();
    void endMatch(ReadableGameState gameState);

    static PuzzleGui of() {
        return new PuzzleGuiImpl();
    }
}
