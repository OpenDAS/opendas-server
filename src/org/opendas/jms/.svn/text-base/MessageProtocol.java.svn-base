package org.opendas.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.opendas.modele.ServerRequest;
import org.opendas.server.ServerLog;

public class MessageProtocol {

	public MessageProtocol() {

	}
	
	public void route(Message message) {

		try{
			if (message instanceof ObjectMessage) {

				ObjectMessage objectMessage = (ObjectMessage) message;

				this.route(objectMessage);

			}
		}catch(Exception e){
			ServerLog.log(this.getClass().getSimpleName(),"Remonté exception route");
		}
		
	}

	public void route(ObjectMessage objectMessage) {

		try {

			Object object = objectMessage.getObject();

			if (object instanceof ServerRequest) {

				this.route((ServerRequest) object);

			}
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param serverRequest
	 */
	public void route(ServerRequest serverRequest) {
		
		
	}

}
