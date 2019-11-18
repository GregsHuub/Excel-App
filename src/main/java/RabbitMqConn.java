import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqConn {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }


//    public Channel rabbitConnProducer(String host, int port) throws IOException, TimeoutException {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost(host);
//        factory.setPort(port);
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//        channel.queueDeclare("products_queue", false, false, false, null);
//        String message = "product details";
//        channel.basicPublish("", "product_queue", null, message.getBytes());
//        channel.close();
//        connection.close();
//
//        return channel;
//
//    }
//
//    public void consumerRabbitMq() throws IOException, TimeoutException {
//        Channel channel = rabbitConnProducer("localhost", 15762);
//        Consumer consumer = new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(
//                    String consumerTag,
//                    Envelope envelope,
//                    AMQP.BasicProperties properties,
//                    byte[] body) throws IOException {
//
//                String message = new String(body, "UTF-8");
//                // process the message
//            }
//        };
//        channel.basicConsume("products_queue", true, consumer);
//
//    }
}
