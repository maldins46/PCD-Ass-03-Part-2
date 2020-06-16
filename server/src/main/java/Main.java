import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

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
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            // test recieve callback
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String receivedMessage = new String(delivery.getBody(), StandardCharsets.UTF_8);
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
