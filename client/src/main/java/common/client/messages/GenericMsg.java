package common.client.messages;


public abstract class GenericMsg implements Message {
    private String sender;

    public GenericMsg(final String sender) {
        this.sender = sender;
    }

    public final String getSender() {
        return sender;
    }
}