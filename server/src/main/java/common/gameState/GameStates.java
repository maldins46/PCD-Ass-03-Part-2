package common.gameState;

/**
 * Game state factory.
 */
public final class GameStates {

    private GameStates() { }

    /**
     * Create an instance of GameState used from server so this can modify the
     * positions of tiles in the puzzle.
     * @return The instance of ModifiableGameState.
     */
    public static PuzzleServiceGameState puzzleService() {
        return new PuzzleServiceGameStateImpl();
    }


    /**
     * Create an instance of GameState used from client so this cant' modify
     * the positions of tiles in the puzzle. This instance can only click on
     * the tiles and server makes the movements.
     * @return The instance of ReadableGameState.
     */
    public static PlayerGameState player() {
        return new PlayerGameStateImpl();
    }
}
