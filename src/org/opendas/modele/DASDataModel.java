package org.opendas.modele;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * Symbolizes a data model 
 */

@Entity
@Table(name = "das_data_model")
public class DASDataModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public DASDataModel() {
	}
	
	public DASDataModel(String code) {
		this.code = code;
	}
	
	@Id
	private Integer id;
	private String code;
	private String name;
	private String type_fct;
	private String type;
	private String source;
	private String ctrl;
	private String fct_name;
	private Integer type_select;
	private Integer max_length;
	private Integer qty_min;
	private Integer qty_max;

	private Integer parent_id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "parent_id", nullable = false)
	private DASDataModel parent;

	@OneToMany(fetch=FetchType.LAZY, mappedBy = "parent")
	@OrderBy(value="parent")
	private List<DASDataModel> childs;

	
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="das_data_model_acquisition_method_rel",
            joinColumns=
                @JoinColumn(name="data_model_id", referencedColumnName="ID"),
            inverseJoinColumns=
                @JoinColumn(name="acquisition_method_id", referencedColumnName="ID")
            )
   	private List<DASAcquisitionMethod> acquisition_methods;
	
	public List<DASAcquisitionMethod> getAcquisitionMethods() {
		return acquisition_methods;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getCtrl() {
		return ctrl;
	}
	public void setCtrl(String ctrl) {
		this.ctrl = ctrl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFctName() {
		return fct_name;
	}
	public void setFctName(String fctName) {
		this.fct_name = fctName;
	}
	public String getTypeFct() {
		return type_fct;
	}
	public void setTypeFct(String typeFct) {
		this.type_fct = typeFct;
	}

	public Integer getTypeSelect() {
		return type_select;
	}
	public void setTypeSelect(Integer typeSelect) {
		this.type_select = typeSelect;
	}
	
	public Integer getMaxLength() {
		return max_length;
	}
	public void setMaxLength(Integer maxLength) {
		this.max_length = maxLength;
	}

	public Integer getQtyMin() {
		return qty_min;
	}
	public void setQtyMin(Integer qty_min) {
		this.qty_min = qty_min;
	}	
	
	public Integer getQtyMax() {
		return qty_max;
	}
	public void setQtyMax(Integer qty_max) {
		this.qty_max = qty_max;
	}	
	
	public DASDataModel getParentId() {
		return parent;
	}
	
	public List<DASDataModel> getChildsId() {
		return childs;		
	}
	
	@Override
	public String toString() {
		return "DASDataModel [id="	+ id
				+ ", name=" + name
				+ ", source=" + source
				+ ", ctrl=" + ctrl 
				+ ", type=" + type
				+ ", fct_name=" + fct_name
				+ ", type_fct="	+ type_fct
				+ ", qty_max="	+ qty_max
				+ ", qty_min="	+ qty_min
				+ ", acquisition_methods="	+ acquisition_methods
				+ ", parent_id="	+ parent_id
				+ ", child_ids="	+ childs			
				+ ", type_select=" + type_select + "]";
	}	
	
	
}
