package org.opendas.modele;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "das_dialog")
public class DASDialog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private String name;

	@Column(name = "send_receive_data")
	private String sendReceiveData;

	private int priority;

	@Column(name = "waiting_second")
	private int waitingSecond;

	private int send;

	@Column(name = "start_char_position")
	private String startCharPosition;

	@Column(name = "stop_char_position")
	private String stopCharPosition;
	
	@Column(name = "header_code")
	private String header_code;

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSendReceiveData() {
		return sendReceiveData;
	}



	public void setSendReceiveData(String sendReceiveData) {
		this.sendReceiveData = sendReceiveData;
	}



	public int getPriority() {
		return priority;
	}



	public void setPriority(int priority) {
		this.priority = priority;
	}



	public int getWaitingSecond() {
		return waitingSecond;
	}



	public void setWaitingSecond(int waitingSecond) {
		this.waitingSecond = waitingSecond;
	}



	public int getSend() {
		return send;
	}



	public void setSend(int send) {
		this.send = send;
	}



	public String getStartCharPosition() {
		return startCharPosition;
	}



	public void setStartCharPosition(String startCharPosition) {
		this.startCharPosition = startCharPosition;
	}



	public String getStopCharPosition() {
		return stopCharPosition;
	}



	public void setStopCharPosition(String stopCharPosition) {
		this.stopCharPosition = stopCharPosition;
	}



	public String getHeader_code() {
		return header_code;
	}



	public void setHeader_code(String headerCode) {
		header_code = headerCode;
	}

	@Override
	public String toString() {
		return "DASDialog ["
				+ "name =" + name
				+ ",header_code=" + header_code
				+ ", sendReceiveData=" + sendReceiveData
				+ ", priority=" + priority 
				+ ", waitingSecond=" + waitingSecond
				+ ", send=" + send
				+ ", startCharPosition=" + startCharPosition
				+ ", stopCharPosition=" + stopCharPosition
				+ "]";
	}

}
