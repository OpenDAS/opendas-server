package org.opendas.jms;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.opendas.modele.ServerRequest;
import org.opendas.server.ServerLoader;
import org.opendas.server.ServerLog;

/**
 * A simple tool for publishing messages
 * 
 * @version $Revision: 1.2 $
 */
public class MessageSender {

	private Destination destination;
	// private int messageCount = 10;
	// private long sleepTime;
	private boolean verbose;

	private long timeToLive;
	private String user;
	private String password;
	private String url;
	private String subject;
	private boolean topic;
	private boolean transacted;
	private boolean persistent;

	private Connection connection = null;

	private static final MessageSender instance = new MessageSender();

	public static MessageSender getInstance() {
		return instance;
	}

	private MessageSender() {

	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public void run() {

		verbose = ServerLoader.debugMode();
		
		ServerLog.log(this.getClass().getSimpleName(),"Connecting sender to URL: " + url);
		
		ServerLog.log(this.getClass().getSimpleName(),"Using " + (persistent ? "persistent" : "non-persistent")
				+ " messages" + " and " + (topic ? "topic" : "queue"));
		
		// Create the connection.
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				user, password, url);

		try {

			this.connection = connectionFactory.createConnection();

			this.connection.start();

		} catch (JMSException e) {
			ServerLog.log(this.getClass().getSimpleName(),"run - JMSException");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param serializable
	 */
	public void send(Serializable serializable, String sujet) {
		send(serializable, sujet, null);
	}
	
	public void send(Serializable serializable, String sujet, String station) {

		if (this.connection == null) {
			this.run();
		}

		try {

			if (timeToLive != 0) {
				log("Messages time to live " + timeToLive + " ms");
			}

			// Create the session
			Session session = connection.createSession(transacted,
					Session.AUTO_ACKNOWLEDGE);
			
			this.destination = this.getDestination(session);

			// Create the producer.
			MessageProducer producer = this.getProducer(session);
		
			// Start sending messages
			ObjectMessage message = session.createObjectMessage();

			message.setStringProperty("Sujet", sujet);
			String log = "Message send having for subject: " + message.getStringProperty("Sujet");
			
			if (station!=null) {
				message.setStringProperty("Station", station);
				log+=("and for workstation: " + message.getStringProperty("Station"));
			}
			logDebug(log);

			message.setObject(serializable);
		

			this.log(message);

			producer.send(message);

			if (transacted) {
				session.commit();
			}

		} catch (Exception e) {
			logErr("Caught: " + e);
			e.printStackTrace();

		}
	}

	/**
	 * @see "http://ydisanto.developpez.com/tutoriels/j2se/serialisation/partie2/"
	 * 
	 * @param object
	 */
	public void sendInXml(Object object) {

		String xml = this.getXMLFromObject(object);

		// Create the session
		Session session;
		try {

			session = connection.createSession(transacted,
					Session.AUTO_ACKNOWLEDGE);

			this.destination = this.getDestination(session);

			// Create the producer
			MessageProducer producer = this.getProducer(session);

			// Start sending message
			TextMessage message = session.createTextMessage(xml);
			
			logErr("test avant log message");
			log(message);

			producer.send(message);

			if (transacted) {
				session.commit();
			}

		} catch (JMSException e) {
			logErr("sendInXml - JMSException");
			e.printStackTrace();
		}

	}

	/**
	 * Transform an object in a serialized xml object
	 * 
	 * @param object
	 * @return
	 */
	private String getXMLFromObject(Object object) {

		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();

		XMLEncoder encoder = new XMLEncoder(byteArray);

		try {
			encoder.writeObject(object);
			encoder.flush();
		} finally {
			encoder.close();
		}

		return byteArray.toString();

	}

	/**
	 * Log a message sending (if verbose)
	 * @param message
	 */
	private void log(Message message) {
		
		if (verbose) {
			if (message instanceof TextMessage) {
				
				String msg = message.toString();
				if (msg.length() > 120) {
					msg = msg.substring(0, 120) + "...";
				}
				logDebug("Sending message: " + msg);
			} else if (message instanceof ObjectMessage) {
				
				ObjectMessage objectMessage = (ObjectMessage) message;
				String msg;
				try {
					msg = objectMessage.getObject().toString();
					if (msg.length() > 120) {
						msg = msg.substring(0, 120) + "...";
					}
					logDebug("Sending message: " + msg);
				} catch (JMSException e) {
					logErr("log - JMSException");
					e.printStackTrace();
				}
			}

		}
		
	}
	


	/**
	 * 
	 * @param session
	 * @return
	 */
	private MessageProducer getProducer(Session session) {

		MessageProducer producer = null;

		try {
			producer = session.createProducer(destination);

			if (persistent) {
				producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			} else {
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			}
			if (timeToLive != 0) {
				producer.setTimeToLive(timeToLive);
			}
		} catch (JMSException e) {
			log("Passage catch getProducer");
			e.printStackTrace();
		}

		return producer;
	}

	/**
	 * 
	 * @param session
	 * @return
	 */
	private Destination getDestination(Session session) {

		Destination destination = null;
		try {
			if (topic) {
				destination = session.createTopic(subject);
			} else {
				destination = session.createQueue(subject);
			}
		} catch (JMSException e) {
			logErr("Error creating Destination");
			e.printStackTrace();
		}

		return destination;
	}

	/**
	 * 
	 * @param serverRequest
	 * @return
	 * @throws TimeoutException 
	 */
	public ServerRequest sendSync(ServerRequest serverRequest, String sujet, String station) throws TimeoutException {
//		// TODO change that (properties parameter) (Destination object)
//		// set the parameters
//		serverRequest.setFromUrl("temp");
//		serverRequest.setSendUrl("temp");
//		System.out.println("----------------------sujet-------"+sujet+"   "+station);
//		// send the message
//		this.send(serverRequest, sujet, station);
//
//		// get the message receiver
//		MessageReceiver mr = MessageReceiver.getInstance();
//		System.out.println("----------------------MessageSender-------"+this.getSubject());
//		System.out.println("----------------------MessageReceiver-------"+mr.getSubject());
//
//		DASResponseListener listener = (DASResponseListener) mr.getListener();
//
//		// wait for the response and return it
//		return listener.getResponse(serverRequest);
		MessageReceiver mr = MessageReceiver.getInstance();
		serverRequest.setFromUrl(mr.getSubject());
		serverRequest.setSendUrl(this.getSubject());
		this.send(serverRequest, sujet, station);
		DASResponseListener listener = (DASResponseListener) mr.getListener();
		return listener.getResponse(serverRequest);	
	}

	public void close() {
		try {
			connection.close();
		} catch (Throwable ignore) {
		}
	}

	private void logDebug(String log) {
		ServerLog.logDebug(getClass().getSimpleName(), log);		
	}
	
	private void log(String log) {
		ServerLog.log(getClass().getSimpleName(), log);
	}
	
	private void logErr(String log) {
		ServerLog.logErr(getClass().getSimpleName(), log);
	}

	public void setPersistent(boolean durable) {
		this.persistent = durable;
	}

	public void setPassword(String pwd) {
		this.password = pwd;
	}

	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}

	public void setTopic(boolean topic) {
		this.topic = topic;
	}

	public void setQueue(boolean queue) {
		this.topic = !queue;
	}

	public void setTransacted(boolean transacted) {
		this.transacted = transacted;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
}
