package org.opendas.modele;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "das_acquisition_method")
public class DASAcquisitionMethod implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	@Id
	private Integer id;
	private String code;
	private String name;
	
	private List<DASDataModel> datamodels;
	@ManyToMany(mappedBy="acquisition_methods",fetch=FetchType.EAGER)
	public List<DASDataModel> getDataModels() {
		return datamodels;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "type_material_id")
    private DASTypeMaterial typeMaterial;
	
	public DASTypeMaterial getTypeMaterial()
	{
		return typeMaterial;
	}
	
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
		return "DASAcquisitionMethod [code=" + code
				+ ", name=" + name
				+ ", typeMaterial=" + typeMaterial
				+ "]";
	}	
	
}
