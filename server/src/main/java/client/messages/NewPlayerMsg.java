package client.messages;


public final class NewPlayerMsg extends GenericMsg {
    private String player;

    public NewPlayerMsg(final String sender, final String player) {
        super(sender);
        this.player = player;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(final String player) {
        this.player = player;
    }
}
