package org.opendas.modele;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Symbolizes a data model 
 * @author mlaroche
 */
@Entity
@Table(name = "das_template_supervision")
public class DASTemplateSupervision implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public DASTemplateSupervision() {
	}
	
	@Id
	private Integer id;
	private String name;
	private String mapping;
	private Integer nb_case_x;
	private Integer nb_case_y;
	private Integer width_case;
	private Integer height_case;
	private byte[] image;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String templateReportName) {
		name = templateReportName;
	}

	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	public Integer getNb_case_x() {
		return nb_case_x;
	}

	public void setNb_case_x(Integer nbCaseX) {
		nb_case_x = nbCaseX;
	}

	public Integer getNb_case_y() {
		return nb_case_y;
	}

	public void setNb_case_y(Integer nbCaseY) {
		nb_case_y = nbCaseY;
	}

	public Integer getWidth_case() {
		return width_case;
	}

	public void setWidth_case(Integer widthCase) {
		width_case = widthCase;
	}

	public Integer getHeight_case() {
		return height_case;
	}

	public void setHeight_case(Integer heightCase) {
		height_case = heightCase;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "DASTemplateSupervision [id=" + id + ", mapping=" + mapping
				+ ", name=" + name + ", nb_case_x=" + nb_case_x
				+ ", nb_case_y=" + nb_case_y + "]";
	}	
	
}
