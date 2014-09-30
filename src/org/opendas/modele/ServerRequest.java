package org.opendas.modele;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * This class permites the communication between DAS_Server and DAS_Client 
 */
public class ServerRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String title;

	/**
	 * destination url
	 */
	private String sendUrl;

	/**
	 * origin url
	 */
	private String fromUrl;

	/**
	 * Request sended date
	 */
	private Date sendedDate;

	/**
	 * Eventual object to transmit
	 */
	private Serializable attachement;

	/**
	 * If this request follow in a demand, it puts the initial request in the response
	 * 
	 */

	private ServerRequest previousRequest;

	public ServerRequest() {
		this.sendedDate = new Date();
	}
	
	public ServerRequest(ServerRequest previousRequest) {
		this();
		previousRequest.setAttachement(null);
		this.previousRequest = previousRequest;
		this.sendUrl = previousRequest.getFromUrl();
		this.fromUrl = previousRequest.getSendUrl();
	}

	public String toString() {
		return "ServerRequest [attachement=" + attachement
		 		+ ", fromUrl=" + fromUrl
		 		+ ", sendUrl=" + sendUrl
				+ ", title=" + title
				+ ", sendedDate=" + sendedDate
				+ ", previousRequest=" + previousRequest
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {

		boolean equal = false;

		if (obj instanceof ServerRequest) {

			ServerRequest sr = (ServerRequest) obj;

			if (this.getFromUrl().equals(sr.getFromUrl())
					&& this.getSendUrl().equals(sr.getSendUrl())
					&& this.getSendedDate().equals(sr.getSendedDate())
					&& this.getTitle().equals(sr.getTitle())) {

				equal = true;
			}

		}

		return equal;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSendUrl() {
		return sendUrl;
	}

	public void setSendUrl(String sendUrl) {
		this.sendUrl = sendUrl;
	}

	public String getFromUrl() {
		return fromUrl;
	}

	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}

	public Date getSendedDate() {
		return sendedDate;
	}

	public void setSendedDate(Date sendedDate) {
		this.sendedDate = sendedDate;
	}

	public Serializable getAttachement() {
		return attachement;
	}

	public void setAttachement(Serializable attachement) {
		this.attachement = attachement;
	}

	public ServerRequest getPreviousRequest() {
		return previousRequest;
	}

	public void setPreviousRequest(ServerRequest previousRequest) {
		this.previousRequest = previousRequest;
	}

}
