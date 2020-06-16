import com.google.gson.Gson;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import messages.NewPlayerMsg;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Main {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        System.out.println("Server ready!");

        ConnectionFactory factory = new ConnectionFactory();
        // todo only in prod factory.setHost("rabbit");
        factory.setHost("localhost");


        try {
            Connection connection = null;
            connection = factory.newConnection();
            Channel channel = connection.createChannel();

            // test send message
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            Gson gson = new Gson();
            NewPlayerMsg msg = new NewPlayerMsg();
            String message = gson.toJson(msg);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            // test recieve callback
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                Gson gson2 = new Gson();
                String receivedMessage = new String(delivery.getBody(), StandardCharsets.UTF_8);
                switch ((String) delivery.getProperties().getHeaders().get("type")) {
                    case "playerSubscription": {
                        NewPlayerMsg newPlayerMsg = gson2.fromJson(receivedMessage, NewPlayerMsg.class);
                        break;
                    }

                    default:

                        break;
                }

                System.out.println(" [x] Received '" + receivedMessage + "'");
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
