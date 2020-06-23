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
    public static ModifiableGameState serverGameState() {
        return new ServerGameState();
    }


    /**
     * Create an instance of GameState used from client so this cant' modify
     * the positions of tiles in the puzzle. This instance can only click on
     * the tiles and server makes the movements.
     * @return The instance of ReadableGameState.
     */
    public static ReplaceableGameState clientGameState() {
        return new ClientGameState();
    }
}
