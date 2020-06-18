package client.messages;

public final class AckMsg extends GenericMsg {
    private String ackedPlayer;

    public AckMsg(final String sender, final String ackedPlayer) {
        super(sender);
        this.ackedPlayer = ackedPlayer;
    }

    public String getAckedPlayer() {
        return ackedPlayer;
    }

    public void setAckedPlayer(final String ackedPlayer) {
        this.ackedPlayer = ackedPlayer;
    }
}
