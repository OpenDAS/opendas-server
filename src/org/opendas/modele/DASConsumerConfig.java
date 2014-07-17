package org.opendas.modele;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a subject to listen by a consumer (Server, Talend, ...)
 */

@Entity
@Table(name = "das_consumer_config")
public class DASConsumerConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String consumer_id;
	@Id
	private String subject;
	
	public String getConsumer_id() {
		return consumer_id;
	}
	public void setConsumer_id(String consumerId) {
		consumer_id = consumerId;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Override
	public String toString() {
		return "DASConsumerConfig [" 
				+ "consumer_id=" + consumer_id
				+ "subject=" + subject
				+ "]";
	}

}
