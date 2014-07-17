package org.opendas.modele;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Symbolizes the mask functional configuration
 */

@Entity
@Table(name = "das_functional_configuration")
public class DASFunctionalConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	@Id
	private Integer id;
	private String mask;
	private String button_xml;
	private String button_bottom_xml;
	private String function_xml;
	private String keyboard_xml;
	
	private List<DASWorkstation> workstations;
	@ManyToMany(mappedBy="functional_configurations")
	public List<DASWorkstation> getWorkstations() {
		return workstations;
	}

	public String getMask() {
		return mask;
	}

	public String getButton_xml() {
		return button_xml;
	}

	public String getButton_bottom_xml() {
		return button_bottom_xml;
	}
	
	public String getFunction_xml() {
		return function_xml;
	}
	
	public String getKeyboard_xml() {
		return keyboard_xml;
	}
	
	@Override
	public String toString() {
		return "DASFunctionalConfig ["
				+ "mask=" + mask 
				+ ", function_xml=" + function_xml 
				+ ", button_xml=" + button_xml
				+ ", button_bottom_xml=" + button_bottom_xml
				+ ", keyboard_xml=" + keyboard_xml
				+ "]";
	}	
	
}
