package org.opendas.modele;

import java.io.Serializable;

/**
 * Symbolizes a error
 */

public class DASError implements Serializable {
	private Integer code;
	private String string;
	private Object object;
	
	public DASError() {
		this.code = null;
		this.string = null;
		this.object = null;
	}
	
	public DASError(Integer code, String string ) {
		this.code = code;
		this.string = string;
	}
	
	public DASError(Integer code, String string, Object object ) {
		this.code = code;
		this.string = string;
		this.object = object;
	}
	
	public Integer getCode() {
		return code;
	}

	public String getString() {
		return string;
	}

	public Object getObject() {
		return object;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}

	public void setString(String string) {
		this.string = string;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	@Override
	public String toString() {
		return "DASError ["
				+ "code=" + code 
				+ ", string=" + string 
				+ ", object=" + object
				+ "]";
	}	
	
}
