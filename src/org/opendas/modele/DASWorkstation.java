package org.opendas.modele;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * Symbolizes a station
 */
@Entity
@Table(name = "das_workstation")
public class DASWorkstation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private String code;
	private String name;
	private String force_pdf;

	private List<DASConfigMaterial> config_ids;
	@OneToMany(mappedBy = "workstation_id")
	private List<DASConfigMaterial> configIds() {
		return config_ids;
	}
	
	@ManyToMany
    @JoinTable(name="das_workstation_das_graphical_configuration",
            joinColumns=
                @JoinColumn(name="workstation_id", referencedColumnName="ID"),
            inverseJoinColumns=
                @JoinColumn(name="graphical_configuration_id", referencedColumnName="ID")
            )
   	private List<DASGraphicalConfig> graphical_configurations;
	
	@ManyToMany
    @JoinTable(name="das_workstation_das_functional_configuration",
            joinColumns=
                @JoinColumn(name="workstation_id", referencedColumnName="ID"),
            inverseJoinColumns=
                @JoinColumn(name="functional_configuration_id", referencedColumnName="ID")
            )
   	private List<DASFunctionalConfig> functional_configurations;

	
//  'config_ids':fields.one2many('das.config.material', 'workstation_id', 'Materials'),
//	'graphical_configuration_ids': fields.many2many('das.graphical.configuration', 'workstation_graphical_configuration_rel', 'workstation_id', 'graphical_configuration_id', 'Graphical configurations'),
//	'functional_configuration_ids': fields.many2many('das.functional.configuration', 'workstation_functional_configuration_rel', 'workstation_id', 'functional_configuration_id', 'Functional configurations'),
	
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
	
	public String getName() {
		return name;
	}

	public String getForce_pdf() {
		return force_pdf;
	}

	@Override
	public String toString() {
		return "DASWorkstation ["
			+ "code = " + code
			+ ", name = " + name
			+ ", force_pdf = " + force_pdf
			+ ", config_ids = " + config_ids
			+ "]";
	}
	
}
