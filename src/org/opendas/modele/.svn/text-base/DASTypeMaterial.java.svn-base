package org.opendas.modele;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "das_type_material")
public class DASTypeMaterial implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String code;
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "DASTypeMaterial ["
				+ "id=" + id
				+ ", name=" + name
				+ "]";
	}

}
