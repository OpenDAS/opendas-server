package org.opendas.ext;

import java.util.List;

import org.opendas.modele.DASDataModel;
import org.opendas.modele.DASEan128;
import org.opendas.modele.DASFunctionalConfig;
import org.opendas.modele.DASGeneric;
import org.opendas.modele.DASGraphicalConfig;
import org.opendas.modele.DASTemplateReport;
import org.opendas.modele.DASTemplateSupervision;
import org.opendas.modele.DASWorkstation;


/**
 * interface permitting of standardize data access for the interface
 */

public interface DASIDataAccess {

	public DASWorkstation getWorkstationWithId(String workstation_code);
	
	public List<DASGeneric> getWsGenericsWithId(String workstation_code);
	
	public List<DASGeneric> getWsGenericsWithIdOrWithout(String workstation_code);

	public DASFunctionalConfig getFctConfigsWithWsId(String workstation_code,String mask);
	
	public DASGraphicalConfig getGraphConfigsWithWsId(String workstation_code,String mask);
	
	public DASDataModel getDataModelWithId(String id);

	public List<String> getConsumConfigWithId(String id);
	
	public List<DASEan128> getListEan128();
	
	public List<DASTemplateReport> getListTemplateReport();
	
	public List<DASTemplateSupervision> getListTemplateSupervision();
	
	public List<DASDataModel> getListDataModel();
}
