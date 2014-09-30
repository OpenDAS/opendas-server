package org.opendas.jms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.naming.TimeLimitExceededException;

import org.opendas.modele.ServerRequest;
import org.opendas.server.ServerLog;

/**
 * Class managing done responses(when this station has sent a message and waits a response)
 * 
 */

public class DASResponseListener implements javax.jms.MessageListener {

	private ArrayList<ServerRequest> listResponse = new ArrayList<ServerRequest>();

	
	private boolean timerOver = false;

	/** Rate at which timer is checked */
	protected int timerRate = 100;

	/** Length of timeout */
	private int timerLength;

	/** Time elapsed */
	private int timeElapsed;

	public void onMessage(Message message) {

		// log("Message received (client) ");

		ObjectMessage om = (ObjectMessage) message;

		ServerRequest sr = null;

		try {

			Serializable serializable = (Serializable) om.getObject();

			sr = (ServerRequest) serializable;

			if (sr != null) {

				this.listResponse.add(sr);
				this.log(message);				
				ServerLog.log(this.getClass().getSimpleName(),"response received");
				
			} else {
				ServerLog.logErr(this.getClass().getSimpleName(),"response received");
			}

		} catch (JMSException e) {
			ServerLog.logErr(this.getClass().getSimpleName(),"JMS Exception");
			e.printStackTrace();
		}

	}

	private void log(Message message) {
		if (message instanceof TextMessage) {
			
			String msg = message.toString();
			if (msg.length() > 120) {
				msg = msg.substring(0, 120) + "...";
			}
			ServerLog.log(getClass().getSimpleName(),"Sending message: " + msg);
			
		} else if (message instanceof ObjectMessage) {
			
			ObjectMessage objectMessage = (ObjectMessage) message;
			String msg;
			try {
				msg = objectMessage.getObject().toString();

				if (msg.length() > 120) {
					msg = msg.substring(0, 120) + "...";
				}
				ServerLog.log(getClass().getSimpleName(),"Sending message: " + msg);
			} catch (JMSException e) {
				ServerLog.logErr(getClass().getSimpleName(),"log - JMSException");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param serverRequest
	 * @return
	 * @throws TimeLimitExceededException 
	 */
	public ServerRequest getResponse(ServerRequest serverRequest) throws TimeoutException {

		ServerLog.log(getClass().getSimpleName(),"getResponse search : " + serverRequest);
		
		ServerRequest response = null;

		int i = 0;
		timerOver = false;
		timeElapsed = 0;

		while (true && timerOver == false) {

			// Put the timer to sleep
			try
			{ 
				Thread.sleep(timerRate);
			}
			catch (InterruptedException ioe) 
			{
				continue;
			}

			// Use 'synchronized' to prevent conflicts
			synchronized ( this )
			{
				// Increment time remaining
				timeElapsed += timerRate;

				// Check to see if the time has been exceeded
				if (timeElapsed > timerLength)
				{
					// Trigger a timeout

					throw new TimeoutException("Timeout");

				}
			}


			// Sleep else not arrived message
			// if not timer which do a pause, others threads can be execute
			try {
				Thread.sleep(5);
			} catch (InterruptedException e1) {
				ServerLog.log(getClass().getSimpleName(),"Exception during the response wait");
				e1.printStackTrace();
			}

			if (this.listResponse.size() > 0) {

//				log("size : " + this.listResponse.size());

				// log("i : " + i + " taille : " + listResponse.size());
				ServerRequest initialRequest = null;
				try {
					ServerRequest temp = this.listResponse.get(i);

					if (temp != null) {
						initialRequest = temp.getPreviousRequest();
					}

				} catch (Exception e) {
					ServerLog.log(getClass().getSimpleName(),"i : " + i + " size : " + listResponse.size());
					e.printStackTrace();
				}

				if (initialRequest != null) {
					// It's the searched response
					if (initialRequest.equals(serverRequest)) {
						response = this.listResponse.get(i);
						// Penser à rajouter et tester une commande pour supprimer 
						// l'objet pour pas que la liste grossisse : 
						this.listResponse.remove(i);

						break;
					}
				}

				// We continue to scan
				if (i + 1 < this.listResponse.size()) {
					i++;
				} else {
					i = 0;
				}
			}
		}

		return response;

	}
	
	public boolean isTimeOutOver() {
		return timerOver;
	}

	public void setTimeOutOver(boolean timeOutOver) {
		this.timerOver = timeOutOver;
	}
	
	public int getTimerLength() {
		return timerLength;
	}

	public void setTimerLength(int timerLength) {
		this.timerLength = timerLength;
	}

}
