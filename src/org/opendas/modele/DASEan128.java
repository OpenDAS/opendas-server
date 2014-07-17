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
@Table(name = "das_ean128")
public class DASEan128 implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String code;
	private String name;
	private Integer max_length;
	
	public String getId() {
		return code;
	}
	public void setId(String code) {
		this.code = code;
	}
	public String getDescription() {
		return name;
	}
	public void setDescription(String name) {
		this.name = name;
	}
	public Integer getMaxLength() {
		return max_length;
	}
	public void setMaxLength(Integer max_lenght) {
		this.max_length = max_lenght;
	}
	
	
	@Override
	public String toString() {
		return "DASListEan128 [name=" + name + ", id=" + code
				+ ", length=" + max_length + "]";
	}
	
	
	
}
