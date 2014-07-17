package org.opendas.modele;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Symbolizes a data model 
 */
@Entity
@Table(name = "das_template_report")
public class DASTemplateReport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public DASTemplateReport() {
	}
	
	@Id
	private Integer id;
	private String name;
	private String xml;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTemplateXml() {
		return xml;
	}
	public void setTemplateXml(String template) {
		this.xml = template;
	}

	public String getName() {
		return name;
	}

	public void setName(String templateReportName) {
		name = templateReportName;
	}

	@Override
	public String toString() {
		return "DASDataModel [id="	+ id
				+ ", name=" + name
				+ ", template=" + xml + "]";
	}	
	
}
