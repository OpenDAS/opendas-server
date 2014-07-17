package org.opendas.modele;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Symbolizes mask graphical configuration for a station
 */

@Entity
@Table(name = "das_graphical_configuration")
public class DASGraphicalConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	@Id
	private Integer id;
	private String mask;
	private String graphical_xml;

	private List<DASWorkstation> workstations;
	@ManyToMany(mappedBy="graphical_configurations")
	public List<DASWorkstation> getWorkstations() {
		return workstations;
	}
	
	public String getMask() {
		return mask;
	}

	public String getGraphical_xml() {
		return graphical_xml;
	}
	
	@Override
	public String toString() {
		return "DASGraphicalConfig [mask=" + mask
				+ ", graphical_xml=" + graphical_xml
				+ "]";
	}	
	
}
