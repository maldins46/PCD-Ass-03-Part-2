package common.gameState;

/**
 * Game state factory.
 */
public class GameStates {
    /**
     * todo
     * @param puzzleWidth
     * @param puzzleHeight
     * @return
     */
    public static ModifiableGameState serverGameState(final int puzzleWidth, final int puzzleHeight) {
        return new ModifiableGameStateImpl(puzzleWidth, puzzleHeight);
    }

    public static ReplaceableGameState clientGameState() {
        return new ReplaceableGameStateImpl();
    }
}
