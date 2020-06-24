package common.client.messages;

import common.model.Player;

/**
 * Message for Ack a change to the puzzle.
 */
public final class AckMsg extends GenericMsg {
    /**
     * Player that receives Ack.
     */
    private final Player ackedPlayer;

    AckMsg(final String sender, final Player ackedPlayer) {
        super(sender);
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
