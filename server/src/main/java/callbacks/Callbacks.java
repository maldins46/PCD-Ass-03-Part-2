package callbacks;

import client.CtxCallback;
import client.GameClient;
import model.GameData;

public class Callbacks {
    public static CtxCallback newPlayerMsg(final GameClient client, final GameData data) {
        return new NewPlayerCallback(client, data);
    }

    public static CtxCallback selectMsg(final GameClient client, final GameData data) {
        return new SelectCallback(client, data);
    }

    public static CtxCallback swapRequestMsg(final GameClient client, final GameData data) {
        return new SwapCallback(client, data);
    }

    public static CtxCallback rematchMsg(final GameClient client, final GameData data) {
        return new RematchCallback(client, data);
    }
}
