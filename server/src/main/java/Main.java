import client.GameClient;
import client.config.Hosts;
import model.GameData;
import callbacks.Callbacks;

public class Main {
    private static GameData gameData;
    private static GameClient gameClient;


    public static void main(final String[] args) {
        System.out.println("Puzzle server started!");

        gameData = new GameData();
        gameClient = new GameClient.Builder()
                .addHost(Hosts.LOCAL)
                .addCallback(Callbacks.newPlayerMsg(gameClient, gameData))
                .addCallback(Callbacks.selectMsg(gameClient, gameData))
                .addCallback(Callbacks.swapRequestMsg(gameClient, gameData))
                .addCallback(Callbacks.rematchMsg(gameClient, gameData))
                .build();

        gameClient.connect();
        System.out.println("Sever ready, waiting for messages");
    }
}
