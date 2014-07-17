package org.opendas.modele;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "das_material")
public class DASMaterial implements Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	@Id
	private Integer id;
	private String code;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "model_material_id")
	private DASModelMaterial modelMaterial;
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public DASModelMaterial getModelMaterial() {
		return modelMaterial;
	}

	@Override
	public String toString() {
		return "DASModelMaterial ["
				+ ", code=" + code
				+ ", name=" + name
				+ ", modelMaterial=" + modelMaterial
				+ "]";
	}
}
