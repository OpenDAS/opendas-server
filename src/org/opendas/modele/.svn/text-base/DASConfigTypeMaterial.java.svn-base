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
import javax.persistence.Table;

/**
 * Material configuration
 */

@Entity 
@Table(name = "das_config_type_material")
public class DASConfigTypeMaterial implements Serializable{

	private static final long serialVersionUID = 8403803244156952804L;
	/**
	 * 
	 */
	@Id /*@GeneratedValue(strategy = GenerationType.IDENTITY)*/
	private Integer id;
	private String code;
	private String name;
	private String port_type;

	private String speed;

	private String parity;

	private String stop_bit;

	private String databit;

	private String flow_control;
	
	private String issimple ;
	
	private String iscumulative ;
	
	@ManyToOne
	@JoinColumn(name = "transmit_protocol_id")
	private DASTransmitProtocol protocol;

	
//	// 'dependencies': fields.many2many('das.generic', 'das_code_dependency', 'code_id','parent_code_id',  'Dependencies')
//	@ManyToMany(fetch=FetchType.EAGER)
//	@JoinTable(name="das_code_dependency",
//			joinColumns=
//				@JoinColumn(name="code_id", referencedColumnName="ID"),
//				inverseJoinColumns=
//					@JoinColumn(name="parent_code_id", referencedColumnName="ID")
//	)
	
	//TODO ML 'dialog_ids': fields.many2many('das.dialog', 'config_type_material_dialog_rel', 'config_type_material_id','dialog_id',  'Dialogs'),
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="das_config_type_material_dialog_rel",
			joinColumns=
				@JoinColumn(name="config_type_material_id", referencedColumnName="ID"),
				inverseJoinColumns=
					@JoinColumn(name="dialog_id", referencedColumnName="ID")
	)
	private List<DASDialog> dialog;
	
	public List<DASDialog> getDialog() {
		return dialog;
	}

	public void setDialog(List<DASDialog> dialog) {
		this.dialog = dialog;
	}

	public String getIssimple() {
		return issimple;
	}

	public void setIssimple(String issimple) {
		this.issimple = issimple;
	}
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="configTypeMaterial")
	private List<DASTypeTransmitProtocol> type_transmit_protocols;
	
	public int getId() {
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

	public void setName(String name) {
		this.name = name;
	}

	public DASTransmitProtocol getProtocol() {
		return protocol;
	}

	public void setProtocol(DASTransmitProtocol protocol) {
		this.protocol = protocol;
	}

	public String getPort_type() {
		return port_type;
	}

	public void setPort_type(String portType) {
		port_type = portType;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getParity() {
		return parity;
	}

	public void setParity(String parity) {
		this.parity = parity;
	}

	public String getStop_bit() {
		return stop_bit;
	}

	public void setStop_bit(String stopBit) {
		stop_bit = stopBit;
	}

	public String getDatabit() {
		return databit;
	}

	public void setDatabit(String databit) {
		this.databit = databit;
	}

	public String getFlow_control() {
		return flow_control;
	}

	public void setFlow_control(String flowControl) {
		flow_control = flowControl;
	}
	public String isSimple() {
		return issimple;
	}

	public void setSimple(String isSimple) {
		this.issimple = isSimple;
	}
	
	public List<DASTypeTransmitProtocol> getType_transmit_protocols() {
		return type_transmit_protocols;
	}

	public String getIscumulative() {
		if(iscumulative ==null){
			return "false";
		}
		return iscumulative;
	}

	public void setIscumulative(String iscumulative) {
		this.iscumulative = iscumulative;
	}

	public void setType_transmit_protocols(
		List<DASTypeTransmitProtocol> type_transmit_protocols) {
		this.type_transmit_protocols = type_transmit_protocols;
	}

	public String toString() {
		return "DASConfigTypeMaterial [code=" + code
			+ ", name=" + name
			+ ", issimple=" + issimple
			+ ", iscumulative=" + iscumulative
			+ ", speed=" + speed
			+ ", parity=" + parity
			+ ", stop_bit=" + stop_bit
			+ ", databit=" + databit
			+ ", dialog=" + dialog
			+ "]";
	}
}
