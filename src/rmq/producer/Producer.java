package rmq.producer;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Producer {
	
	private final static String QUEUE_NAME = "queue4";

	  public static void main(String[] argv) throws java.io.IOException, TimeoutException {
		  ConnectionFactory factory = new ConnectionFactory();
		    factory.setHost("test-services-ubuntu1404");
		    factory.setPassword("guest");
		    factory.setUsername("guest");
		    Connection connection = factory.newConnection();
		    Channel channel = connection.createChannel();
		    
		    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		    String message = "Hello World!";
		    for (int i=0; i<100; i++)
		    	channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		    System.out.println(" [x] Sent '" + message + "'");
		    
		    channel.close();
		    connection.close();
	  }

}
