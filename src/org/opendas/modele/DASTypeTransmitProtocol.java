package org.opendas.modele;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "das_type_transmit_protocol")
public class DASTypeTransmitProtocol implements Serializable{

	private static final long serialVersionUID = 7791281870443217338L;

	@Id
	private Integer id;
	private String type;
	private String name;
	private String language;
	private Integer sequence;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "config_type_material_id")
	private DASConfigTypeMaterial configTypeMaterial;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DASConfigTypeMaterial getConfigTypeMaterial() {
		return configTypeMaterial;
	}
	public void setConfigTypeMaterial(DASConfigTypeMaterial configTypeMaterial) {
		this.configTypeMaterial = configTypeMaterial;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String toString() {
		return "DASTypeTransmitProtocol [id=" + id + ", type=" + type
				+ ", name=" + name + ", sequence=" + sequence
				+ ", configTypeMaterial=" + configTypeMaterial + "]"
				+ ", language=" + language + "]";
	}

}
