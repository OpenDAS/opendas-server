package org.opendas.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Symbolizes a generic, that is a generic element carrying a data
 */

@Entity
@Table(name = "das_generic")
public class DASGeneric implements Serializable {

	private static final long serialVersionUID = 1L;
	@Transient
	private HashMap<String, Object>	infos = null;

	public DASGeneric() {
	}

	public DASGeneric(String code, DASDataModel dataModel) {
		this.code = code;
		this.dataModel = dataModel;
		this.infos = new HashMap<String, Object>();
	}

	@SuppressWarnings("unused")
	@Id
	private Integer id;
	private String code;
	private String name;
	private String image;
	private Integer page;
	private Integer position;
	private Integer qty_min = 0;
	private Integer qty_max = 0;


	@ManyToOne
	@JoinColumn(name = "data_model_id", nullable = false)
	private DASDataModel dataModel;

	private Integer workstation_id;
	@ManyToOne
	@JoinColumn(name = "workstation_id", nullable = false)
	private DASWorkstation workstation;

	// 'dependencies': fields.many2many('das.generic', 'das_code_dependency', 'code_id','parent_code_id',  'Dependencies')
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="das_code_dependency",
			joinColumns=
				@JoinColumn(name="code_id", referencedColumnName="ID"),
				inverseJoinColumns=
					@JoinColumn(name="parent_code_id", referencedColumnName="ID")
	)
	private List<DASGeneric> codeDependency;

	public List<DASGeneric> getCodeDependency() {
		return codeDependency;
	}

	public void setCodeDependency(String str) {
		codeDependency = new LinkedList<DASGeneric>();
		DASGeneric tmp = new DASGeneric(str,this.getData_model());
		tmp.setName(str);
		codeDependency.add(tmp);
	}
	
	public void setListCodeDependency(List<DASGeneric> val) {
		List<DASGeneric> tmpVal = new ArrayList<DASGeneric>();
		tmpVal.addAll(val);
		codeDependency = tmpVal;
	}
	
	public DASDataModel getData_model() {
		return dataModel;
	}
	public void setData_model(DASDataModel data_model) {
		dataModel = data_model;
	}
	public DASWorkstation getWorkstation() {
		if (workstation==null) {
			workstation = new DASWorkstation();
			workstation.setCode("-1");
		}
		return workstation;
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

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
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

	//TODO Applique saut de ligne (un <br/> sans doute) alors qu'il n'y à qu'un nom
	public String getHtmlName(int nbchar) {
		String result = "" ;
		try{
		result += "<html><center>";
		String name = this.getName();
		String[] name_tab = name.split("<br/>");

		int numCharPlaced = 0;
		
		// It processes each line by default
		for(int i = 0 ; i< name_tab.length ; ++i)
		{
			String[] wordTab = name_tab[i].split(" ");
			
			//It processes each words
			for(int j = 0 ; j< wordTab.length ; ++j){
				
				//If the words hasn't cut,it adds
				if(wordTab[j].length()+numCharPlaced <= nbchar){
					numCharPlaced = numCharPlaced + wordTab[j].length() + 1;
					result += wordTab[j]+" ";
				
				//Else go at the line
				}else{
					numCharPlaced = 0;
					result += "<br/>";
					numCharPlaced = numCharPlaced + wordTab[j].length() + 1;
					result += wordTab[j]+" ";
				}		
				
			}
			result += "<br/>";
		}
		if(this.getQtyMin() != null && this.getQtyMax() != null){
		if (this.getQtyMax() <= 0 && this.getQtyMin() <= 0)
		{
		} 
		else 
		{
			if (this.getQtyMax().equals(this.getQtyMin()))
			{
				result += "Qty :";
				result += this.getQtyMax();
			} 
			else
			{
				result += "Qty min :";
				result += this.getQtyMax();
				result += ", Qty max :";
				result += this.getQtyMin();
			}
		}
		}else{
			throw new Exception();
		}
		result += "</center></html>";
		}catch (Exception e) {
			return "<html><center>"+this.getName()+"</center></html>";
		}
		
		return result;
	}	

//	public String getHtmlNameOld(int nbchar) {
//		String result = "" ;
//		result += "<html><center>";
//		String name = this.getName();
//		String[] name_tab = name.split("<br/>");
//
//		for(int i = 0 ; i< name_tab.length ; ++i,result += "<br/>")
//		{
//			for(int j = 0 ; j< name_tab[i].length() ; ++j)
//			{
//				result += name_tab[i].charAt(j);
//				if (j!= 0 && j % nbchar == 0 )
//				{
//					result += "<br/>";
//				}
//			}
//		}
//
//		if (this.getQtyMax() <= 0 && this.getQtyMin() <= 0)
//		{
//		} 
//		else 
//		{
//			if (this.getQtyMax().equals(this.getQtyMin()))
//			{
//				result += "<br> Qty :";
//				result += this.getQtyMax();
//			} 
//			else
//			{
//				result += "<br> Qty min :";
//				result += this.getQtyMax();
//				result += "<br> Qty max :";
//				result += this.getQtyMin();
//			}
//		}
//		result += "</center></html>";
//		return result;
//	}	

	@Override
	public String toString() {
		return "DASGeneric [code=" + code
		+ ", name=" + name
		+ ", workstation_id=" + workstation_id
		+ ", image=" + image
		+ ", page=" + page
		+ ", position=" + position
		+ ", qty_max=" + qty_max
		+ ", qty_min=" + qty_min
		+ ", das_code_dependency=" + codeDependency
		+ ", dataModel=" + dataModel
		+ ", infos=" + infos
		+ "]";
	}

	public void setInfos(HashMap<String, Object> infos) {
		this.infos = infos;
	}

	public HashMap<String, Object> getInfos() {
		return infos;
	}

}
