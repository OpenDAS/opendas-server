package org.opendas.modele;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author jshederer
 *
 */
@Entity
@Table(name = "das_model_material")
public class DASModelMaterial implements Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	@Id
	private Integer id;
	private String code;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "type_material_id")
	private DASTypeMaterial typeMaterial; 
				
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public DASTypeMaterial getTypeMaterial() {
		return typeMaterial;
	}

	public void setTypeMaterial(DASTypeMaterial typeMaterial) {
		this.typeMaterial = typeMaterial;
	}


	@Override
	public String toString() {
		return "DASModelMaterial ["
				+ ", typeMaterial=" + typeMaterial
				+ "]";
	}	
	
}
