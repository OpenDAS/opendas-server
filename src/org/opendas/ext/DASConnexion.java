package org.opendas.ext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.ImageIcon;

import oracle.toplink.essentials.internal.databaseaccess.DatabaseAccessor;
import oracle.toplink.essentials.internal.ejb.cmp3.base.EntityManagerImpl;
import oracle.toplink.essentials.internal.sessions.UnitOfWorkImpl;
import oracle.toplink.essentials.sessions.Session;

import org.opendas.modele.DASConfigMaterial;
import org.opendas.modele.DASDataModel;
import org.opendas.modele.DASEan128;
import org.opendas.modele.DASFunctionalConfig;
import org.opendas.modele.DASGeneric;
import org.opendas.modele.DASGraphicalConfig;
import org.opendas.modele.DASTemplateReport;
import org.opendas.modele.DASTemplateSupervision;
import org.opendas.modele.DASWorkstation;
import org.opendas.modele.WsConfig;
import org.opendas.server.ServerLog;

/**
 * Class of Data Access Object
 * 
 * C'est a singleton, to get a reference :
 * <code>DASConnexion connexion = DASConnexion.getInstance();</code>
 * 
 * @author hloiret
 * @author mlaroche
 * 
 */

public class DASConnexion implements DASIDataAccess {

	/**
	 * Singleton
	 */
	private static DASConnexion instance = new DASConnexion();

	public EntityManager em;

	/**
	 * 
	 */
	private DASConnexion() {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("DAS");

		this.em = emf.createEntityManager();

	}

	/**
	 * Access at singleton
	 * 
	 * @return DASConnexion
	 */
	public static DASConnexion getInstance() {
		ServerLog.logDebug(instance.getClass().getSimpleName(),"APPEL GET INSTANCE :: DASCONNEXION");
		return DASConnexion.instance;
	}
	
	/**
	 * Return the workstation of which the code has been passed in parameter
	 * @param workstation_code the identify (code) of the workstation what we wish had
	 * @return the station of which the code has been passed in parameter
	 */
	private DASWorkstation getWorkstation(String workstation_code) {
		Query query = this.em.createQuery("SELECT w FROM DASWorkstation w "
				+ "WHERE w.code = :code");

		query.setParameter("code", workstation_code);

		DASWorkstation workstation = null;
		try {
			workstation = (DASWorkstation) query.getSingleResult();
		}
		catch (NoResultException e) {
			ServerLog.logErr(instance.getClass().getSimpleName(),"Unknown workstation :"+workstation_code);
		}

		return workstation;
	}
	
	/**
	 * Get back coresponding object at the workstation
	 * @param workstation_code
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	private List<DASGeneric> getGenerics0(String workstation_code) {
		
		Query query = this.em.createQuery("SELECT g FROM DASGeneric g "
				+ "WHERE g.workstation.code = :code");

		query.setParameter("code", workstation_code);

		List<DASGeneric> generics = query.getResultList();
		
		return generics;
	}
	

	/**
	 * Get back coresponding object at the workstation and objects by default
	 * @param workstation_code
	 * @return
	 */

	@SuppressWarnings("unchecked")
	private List<DASGeneric> getGenerics1(String workstation_code) {
		
		//TODO Voir comment la selection des generics n'ayant pas de workstation_code est possible
//		Query query = this.em.createQuery("SELECT g FROM DASGeneric g "
//			+ "WHERE g.workstation_code IS NULL "
//			+ "or g.workstation.code = :code");
//
//		query.setParameter("code", workstation_code);
//		
//		List<DASGeneric> generics = query.getResultList();
			
//		Session session = ((EntityManagerImpl) em).getSession();
//		List gen = session.readAllObjects(DASGeneric.class);
		
		//System.out.println("gen : "+gen);

//		session.getIdentityMapAccessor().invalidateClass(DASGeneric.class,false);
//		List gen2 = session.readAllObjects(DASGeneric.class);
//		System.out.println("gen : "+gen);

		ServerLog.logDebug(instance.getClass().getSimpleName(),"GET GENERICS ##");
		Query query = this.em.createQuery("SELECT g FROM DASGeneric g ");

//		List gen = session.readAllObjects(DASGeneric.class);
//		for(Object i : gen){
//			session.refreshObject(i);
//		}
		
		//session.refreshObject(fct_con);
		//session.refreshObject(DASGeneric.class);

		//System.out.println("SESSION :"+session);
		//System.out.println("fct_con :"+fct_con);

	    query.setHint("toplink.refresh", "true");
		
		List<DASGeneric> allGenerics = query.getResultList();
		
		//System.out.println("================> ALLGENERICS : "+allGenerics);
		
		List<DASGeneric> generics = new ArrayList<DASGeneric>();
		for (DASGeneric generic : allGenerics) {
			String code = generic.getWorkstation().getCode();
			if (code.equals("-1")||code.equals(workstation_code)) {
				generics.add(generic);
			}
		}
		
		return generics;
	}
	/**
	 * Return workstation functional configurations which of the code has been given in parameter
	 * @param workstation_code the workstation code asking its functional configurations
	 * @return workstation functional configurations which of the code has been given in parameter
	 */

	private DASFunctionalConfig getFctConfigs(String workstation_code,String mask) {
			
		Query query = this.em.createQuery("SELECT w.functional_configurations FROM DASWorkstation w "
				+ "WHERE w.code = :workstation_code");
		query.setParameter("workstation_code", workstation_code);	
		//query.setHint("toplink.refresh", "true");
		
		List<DASFunctionalConfig> functionalConfigs = query.getResultList();
		DASFunctionalConfig functionalConfig = null;

		for(DASFunctionalConfig funcConf : functionalConfigs){
			if(funcConf.getMask().equals(mask)){
				functionalConfig = funcConf;
				break;
			}
		}
		return functionalConfig;
	}

	private DASGraphicalConfig getGraphConfigs(String workstation_code,String mask) {
		
		Query query = this.em.createQuery("SELECT w.graphical_configurations FROM DASWorkstation w "
				+ "WHERE w.code = :workstation_code");
		query.setParameter("workstation_code", workstation_code);

		List<DASGraphicalConfig> graphicConfigs = query.getResultList();
		DASGraphicalConfig graphicalConfig = null;

		for(DASGraphicalConfig graphConf : graphicConfigs){
			if(graphConf.getMask().equals(mask)){
				graphicalConfig = graphConf;
				break;
			}
		}
		
		return graphicalConfig;
	}
	
	private DASDataModel getDataModel(String dataModelCode) {
		Query query = this.em.createQuery("SELECT dm FROM DASDataModel dm " + "WHERE dm.code = :code");

		query.setParameter("code", dataModelCode);

		DASDataModel dataModel = null;
		try {
			dataModel = (DASDataModel) query.getSingleResult();
			System.out.println("DATA MODEL :"+dataModel.toString());
		} catch (NoResultException e) {
			ServerLog.logDebug(instance.getClass().getSimpleName(),"data model '" + dataModelCode + "' unknown");
		}
		return dataModel;
	}

	
	public List<DASEan128> getListEan128() {
		
		Query query = this.em.createQuery("SELECT dm FROM DASEan128 dm");

		List<DASEan128> EAN128List = (List<DASEan128>) query.getResultList();
		
		return EAN128List;
	}
	
	public List<DASTemplateReport> getListTemplateReport() {
		
		ServerLog.logDebug(instance.getClass().getSimpleName()," ===== GETLTP");
		Query query = this.em.createQuery("SELECT dm FROM DASTemplateReport dm");

		List<DASTemplateReport> templateReportList = (List<DASTemplateReport>) query.getResultList();
		ServerLog.logDebug(instance.getClass().getSimpleName()," ===== : "+templateReportList);
		
		return templateReportList;
	}
	
	public List<DASTemplateSupervision> getListTemplateSupervision() {
		
		ServerLog.logDebug(instance.getClass().getSimpleName()," ===== GETLTS");
		
		Query query = this.em.createQuery("SELECT dm FROM DASTemplateSupervision dm");

		List<DASTemplateSupervision> templateSupervisionList = (List<DASTemplateSupervision>) query.getResultList();
		
		ServerLog.logDebug(instance.getClass().getSimpleName()," ===== : "+templateSupervisionList);
		
		return templateSupervisionList;
	}
	
	public ImageIcon getImageTemplateSupervision(String name) {

		try {

			/* For insert a image on the db
			File image = new File("/mnt/data/workspace/DAS/ressources/supervision_usine_1.jpg");

	        InputStream is = new FileInputStream(image);

	        PreparedStatement ps = this.getConnection().prepareStatement("INSERT INTO das_template_supervision(name, image) VALUES (?, ?)");
	        ps.setString(1, "supervision_usine_1");
	        ps.setBinaryStream(2, is, (int)image.length());
	        ps.executeUpdate();
			*/

			PreparedStatement ps = this.getConnection().prepareStatement("SELECT image FROM das_template_supervision WHERE name = '"+name+"'");
			ResultSet rs = ps.executeQuery();
	        
			if(rs.next()){
			    
				  byte[] bytes = rs.getBytes(1);
				
					if(bytes != null){
						return new ImageIcon(bytes);
					}
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<DASDataModel> getListDataModel() {

		Query query = this.em.createQuery("SELECT dm FROM DASDataModel dm");

		List<DASDataModel> DataModel = query.getResultList();
		
		//System.out.println(DataModel);
		
		return DataModel;
	}


	
	@SuppressWarnings("unchecked")
	private List<String> getConsumConfig(String id) {
		Query query = this.em.createQuery("SELECT cc.subject FROM DASConsumerConfig cc "
				+ "WHERE cc.consumer_id = :id");

		query.setParameter("id", id);

		List<String> listSubjects = query.getResultList();
		
		return listSubjects;
	}
	

	public DASWorkstation getWorkstationWithId(String workstation_code) {
		return getWorkstation(workstation_code);
	}

	public List<DASGeneric> getWsGenericsWithId(String workstation_code) {
		return getGenerics0(workstation_code);
	}
	
	public List<DASGeneric> getWsGenericsWithIdOrWithout(String workstation_code) {
		return getGenerics1(workstation_code);
	}
	
	public DASFunctionalConfig getFctConfigsWithWsId(String workstation_code,
			String mask) {
		return getFctConfigs(workstation_code,mask);
	}
	
	public DASGraphicalConfig getGraphConfigsWithWsId(String workstation_code,String mask) {
		return getGraphConfigs(workstation_code,mask);
	}
	
	public DASDataModel getDataModelWithId(String id) {
		return getDataModel(id);
	}
	
	public List<String> getConsumConfigWithId(String id) {
		return getConsumConfig(id);
	}
	

	/**
	 * 
	 * @param workstation
	 * @return
	 */

	public WsConfig getWsConfig(DASWorkstation workstation) {

		WsConfig config = new WsConfig();
		Query query = this.em.createQuery("SELECT c FROM DASConfigMaterial c "
				+ "WHERE c.workstation = :workstation");

		query.setParameter("workstation", workstation);
		
		List<DASConfigMaterial> list = query.getResultList();
		config.setConfigMaterial(list);

		return config;

	}

	/**
	 * Retourne la chaine qui sert à parser la chaine de la balance
	 * @return expression régulière
	 */
	
	//TODO Vérifier si utilisé normalement non
	public String recupChaineParser(DASWorkstation dasWorkstation) {

		String result = "";

		// TODO distinction balance ?

		String requete = "SELECT dia.send_receive_data "
				+ "FROM DAS_CONFIG_MATERIAL conf JOIN DAS_DIALOG dia "
				+ "ON conf.dialog_id = dia.code "
				+ "JOIN DAS_MODEL_MATERIAL mod "
				+ "ON mod.code = conf.model_material_id "
				+ "JOIN DAS_TYPE_MATERIAL type "
				+ "ON type.code = mod.type_material_id "
				+ "WHERE conf.workstation_code = '" + dasWorkstation.getCode()
				+ "';";

		PreparedStatement stmtCategorie;
		try {

			stmtCategorie = this.getConnection().prepareStatement(requete);

			ResultSet srs = stmtCategorie.executeQuery();

			while (srs.next()) {
				result = srs.getString("send_receive_data");
			}

		} catch (SQLException e) {
			ServerLog.logDebug(instance.getClass().getSimpleName(),"Error during data access");		
			e.printStackTrace();
		}

		return result;

	}
	
	/**
	 * Specific Toplink !! Permites the connection reception to execute sql requests
	 * @return
	 */
	
	public Connection getConnection() {
		ServerLog.logDebug(instance.getClass().getSimpleName(),"APPEL GETCONNECTION");
		Session session = ((EntityManagerImpl) em).getSession();

		UnitOfWorkImpl uow = (UnitOfWorkImpl) session;
		
		DatabaseAccessor dbAcc = (DatabaseAccessor) uow.getAccessor();

		Connection connUOW = dbAcc.getConnection();

		return connUOW;

	}

}
