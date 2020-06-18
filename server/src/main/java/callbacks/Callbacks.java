package callbacks;

import client.CtxCallback;
import client.GameClient;
import model.GameData;

public class Callbacks {
    public static CtxCallback newPlayer(final GameClient client, final GameData data) {
        return new NewPlayerCallback(client, data);
    }

    public static CtxCallback selectRequest(final GameClient client, final GameData data) {
        return new SelectRequestCallback(client, data);
    }

    // todo
}
