package org.opendas.jms;

import java.util.List;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.benchmark.Consumer;
import org.opendas.server.ServerLoader;
import org.opendas.server.ServerLog;

/**
 * Class permitting the message reception
 */

public class MessageReceiver implements ExceptionListener{

	private boolean running;

	Connection connection;
	MessageConsumer consumer = null;
	private Session session;
	private Destination destination;
	private MessageProducer replyProducer;
	private ActiveMQConnectionFactory connectionFactory;
	private String subject;
	private String user;
	private String password;
	private String url;
	private boolean transacted;

	private MessageListener listener;

	private int ackMode = Session.AUTO_ACKNOWLEDGE;
	private static String sujetFormate;

	private final static MessageReceiver instance = new MessageReceiver();

	/**
	 * 
	 */
	private MessageReceiver() {

	}

	public static MessageReceiver getInstance() {
		return instance;
	}

	public static MessageReceiver getInstance(List<String> sujets) {
		for (String s : sujets) {
			if (sujetFormate == null) {
				sujetFormate = "Sujet = '" + s + "'";
			} else {
				sujetFormate += " or Sujet = '" + s + "'";
			}
		}

		ServerLog.log(instance.getClass().getSimpleName(),sujetFormate);
		
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void run() throws Exception{
		try {
			running = true;
			ServerLog.log(instance.getClass().getSimpleName(),"Connecting receiver to URL: "+ url);
			ServerLog.log(instance.getClass().getSimpleName(),"Consuming queue : " + subject);
			
			connectionFactory = new ActiveMQConnectionFactory(user, password, url);
			connection = connectionFactory.createConnection();

			connection.setExceptionListener(this);
			connection.start();
			session = connection.createSession(transacted, ackMode);

			destination = session.createQueue(subject);

			replyProducer = session.createProducer(null);
			replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			consumer = session.createConsumer(destination, sujetFormate);
			// consumer = session.createConsumer(destination);
			consumer.setMessageListener(this.getListener());

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onException(JMSException e) {
		ServerLog.log(instance.getClass().getSimpleName(),"Passage par onException");
		e.printStackTrace();
	}

	synchronized boolean isRunning() {
		return running;
	}

	public Destination getDestination() {
		return this.destination;
	}

	public void setListener(MessageListener listener) {
		ServerLog.log(instance.getClass().getSimpleName(),"LISTENER SETTED");
		this.listener = listener;
	}

	public MessageListener getListener() {
		return this.listener;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
