package common.gameState;

/**
 * Game state factory.
 */
public class GameStates {
    /**
     * todo
     * @return
     */
    public static ModifiableGameState serverGameState() {
        return new ServerGameState();
    }

    public static ReplaceableGameState clientGameState() {
        return new ClientGameState();
    }
}
