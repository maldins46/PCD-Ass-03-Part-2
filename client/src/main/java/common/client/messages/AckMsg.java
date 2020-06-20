package common.client.messages;

import common.model.Player;

public final class AckMsg extends GenericMsg {
    private Player ackedPlayer;

    public AckMsg(final String sender, final Player ackedPlayer) {
        super(sender);
        this.ackedPlayer = ackedPlayer;
    }

    public Player getAckedPlayer() {
        return ackedPlayer;
    }
}
