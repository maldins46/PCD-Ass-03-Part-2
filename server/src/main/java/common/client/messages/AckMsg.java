package common.client.messages;

import common.model.Player;

public final class AckMsg extends GenericMsg {
    /**
     * Player that receives Ack.
     */
    private final Player ackedPlayer;

    AckMsg(final String sender, final Player ackedPlayer) {
        super(sender);
        this.ackedPlayer = ackedPlayer;
    }

    public Player getAckedPlayer() {
        return ackedPlayer;
    }
}
