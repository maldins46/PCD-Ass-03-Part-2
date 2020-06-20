package common.client.messages;


import common.model.Player;

public final class NewPlayerMsg extends GenericMsg {
    private Player player;

    public NewPlayerMsg(final String sender, final Player player) {
        super(sender);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
