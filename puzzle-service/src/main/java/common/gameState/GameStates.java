package common.gameState;

/**
 * Factory used to create instances of the game state, usable by the puzzle
 * service or the player. Each typology is differs in how is possible to
 * manipulate the structure.
 */
public final class GameStates {

    private GameStates() { }

    /**
     * Create an instance of GameState used from the puzzle service that brings
     * the possibility to modify the structure in deep.
     * @return The instance of the game state.
     */
    public static PuzzleServiceGameState puzzleService() {
        return new PuzzleServiceGameStateImpl();
    }


    /**
     * Create an instance of GameState used from the player, non-modifiable in
     * its core characteristics, but with some useful endpoints for information
     * access.
     * @return he instance of the game state.
     */
    public static PlayerGameState player() {
        return new PlayerGameStateImpl();
    }
}
