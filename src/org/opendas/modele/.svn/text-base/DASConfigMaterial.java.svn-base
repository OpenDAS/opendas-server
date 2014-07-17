package org.opendas.modele;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "das_config_material")
public class DASConfigMaterial implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private String code;
	private String name;
	private String port;
	
	@ManyToOne
	@JoinColumn(name = "workstation_id", nullable = false)
	private DASWorkstation workstation;

	@ManyToOne
	@JoinColumn(name = "material_id", nullable = false)
	private DASMaterial material;

	@ManyToOne
	@JoinColumn(name = "config_type_material_id", nullable = false)
	private DASConfigTypeMaterial configTypeMaterial;


	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DASWorkstation getWorkstation() {
		return workstation;
	}

	public void setWorkstation(DASWorkstation workstation) {
		this.workstation = workstation;
	}
	
	

	public DASMaterial getMaterial() {
		return material;
	}

	public void setMaterial(DASMaterial material) {
		this.material = material;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public DASConfigTypeMaterial getConfigTypeMaterial() {
		
		return configTypeMaterial;
	}
	
	@Override
	public String toString() {
		return "DASConfigMaterial [code=" + code
				+ ", name=" + name
				+ ", port=" + port
				+ ", workstation=" + workstation
				+ ", material=" + material
				+ ", configTypeMaterial=" + configTypeMaterial
				+ "]";
	}

	public String getTypeMaterialName() {
		return this.getMaterial().getModelMaterial().getTypeMaterial().getName();
	}
}
