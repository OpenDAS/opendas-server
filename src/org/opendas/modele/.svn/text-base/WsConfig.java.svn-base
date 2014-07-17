package org.opendas.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Stocks DAS station configuration
 */

public class WsConfig implements Serializable {


	private static final long serialVersionUID = 1L;


	private List<DASConfigMaterial> configMaterial = new ArrayList<DASConfigMaterial>();


	public void addDASConfigMaterial(DASConfigMaterial configMaterial){
		this.configMaterial.add(configMaterial);
	}

	public void setConfigMaterial(List<DASConfigMaterial> configMaterial){
		this.configMaterial = configMaterial;
	}

	public String toString() {
		return "WsConfig [configMaterial=" + configMaterial.toString() + "]";
	}

	/**
	 * Return materials of the station
	 * @return materials of the station
	 */
	
	public List<DASConfigMaterial> getMaterials() {
		List<DASConfigMaterial> materials = new ArrayList<DASConfigMaterial>();

		for (DASConfigMaterial temp : this.configMaterial) {
			System.out.println(temp.getCode() +":"+temp.getConfigTypeMaterial().getIscumulative());
			materials.add(temp);
		}
		return materials;
	}

	/**
	 * Return the station supervisors
	 * @return station supervisors
	 */
	public List<DASConfigMaterial> getSupervisor(){

		List<DASConfigMaterial> supervisors = new ArrayList<DASConfigMaterial>();

		for(DASConfigMaterial temp : this.configMaterial){
			if("Supervisor".equalsIgnoreCase(temp.getTypeMaterialName())){
				supervisors.add(temp);
				break;
			}
		}
		
		return supervisors;
		
	}

	public List<DASConfigMaterial> getPrinters(){

		List<DASConfigMaterial> printers = new ArrayList<DASConfigMaterial>();
		for(DASConfigMaterial temp : this.configMaterial){
			if("printing by ink-jet".equalsIgnoreCase(temp.getTypeMaterialName())){
				printers.add(temp);
				break;
			}
		}
		return printers;

	}

}
