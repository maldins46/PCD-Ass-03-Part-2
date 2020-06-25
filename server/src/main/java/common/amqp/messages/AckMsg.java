package common.amqp.messages;

import common.model.Player;

/**
 * Message for Ack a change to the puzzle.
 */
public final class AckMsg implements PuzzleServiceMsg {
    /**
     * Player that receives Ack.
     */
    private final Player ackedPlayer;

    AckMsg(final Player ackedPlayer) {
        this.ackedPlayer = ackedPlayer;
    }

    /**
     * Get's the acked player.
     * @return The acked's player.
     */
    public Player getAckedPlayer() {
        return ackedPlayer;
    }
}
