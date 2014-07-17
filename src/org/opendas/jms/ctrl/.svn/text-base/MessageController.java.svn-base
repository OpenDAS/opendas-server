package org.opendas.jms.ctrl;

import java.io.Serializable;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.opendas.ext.DASConnexion;
import org.opendas.jms.MessageSender;
import org.opendas.modele.DASDataModel;
import org.opendas.modele.DASEan128;
import org.opendas.modele.DASFunctionalConfig;
import org.opendas.modele.DASGeneric;
import org.opendas.modele.DASGraphicalConfig;
import org.opendas.modele.DASTemplateReport;
import org.opendas.modele.DASTemplateSupervision;
import org.opendas.modele.DASWorkstation;
import org.opendas.modele.ServerRequest;
import org.opendas.modele.WsConfig;
import org.opendas.server.ServerLoader;
import org.opendas.server.ServerLog;

/**
 * Controller managing received messages
 */

public class MessageController implements MessageListener {

	private MessageSender messageSender;

	private DASConnexion conn = DASConnexion.getInstance();

	public MessageController() {
		this.messageSender = MessageSender.getInstance();
	}

	public void route(Message message) {
		
		try {	
			ServerLog.logDebug(this.getClass().getSimpleName(),"Subject = " + message.getStringProperty("Sujet"));
			
		} catch (JMSException e) {
			ServerLog.logDebug(this.getClass().getSimpleName(),"Message subject reading error");
			e.printStackTrace();
		}
		
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;

			this.route(objectMessage);

		}
	}

	/**
	 * 
	 * @param objectMessage
	 */
	public void route(ObjectMessage objectMessage) {

		try {

			Object object = objectMessage.getObject();

			if (object instanceof ServerRequest) {

				this.route((ServerRequest) object, objectMessage.getStringProperty("Station"));

			}

		} catch (JMSException e) {
			ServerLog.logErr(this.getClass().getSimpleName(),"exception route");
			e.printStackTrace();
		}
	}

	public void route(ServerRequest serverRequest, String station) {
		ServerLog.logDebug(getClass().getSimpleName(),"Message received : " + serverRequest);
		
		String title = serverRequest.getTitle();
		ServerLog.log(this.getClass().getSimpleName(),"TITLE ====>"+title);
//		if ("getListeArticlesAvecProvenance".equals(title)) {
//
//			int id = (Integer) serverRequest.getAttachement();
//
//			List<DASArticle> list = this.conn.RecupListeArticlesAvecProvenance(id);
//
//			sendResponse(serverRequest, (Serializable) list, station);
//
//		} else if ("getArticleAvecId".equals(title)) {
//
//			int id = (Integer) serverRequest.getAttachement();
//
//			DASArticle article = this.conn.RecupArticleAvecId(id);
//
//			sendResponse(serverRequest, article, station);
//
//		} else if ("getLotAvecSSCC".equals(title)) {
//
//			String id_sscc = (String) serverRequest.getAttachement();
//
//			DASSscc lot = this.conn.RecupLotAvecSSCC(id_sscc);
//
//			sendResponse(serverRequest, lot, station);
//
//		} else 
//		if ("getChaineParser".equals(title)) {
//
//			DASWorkstation d = new DASWorkstation();
//			d.setId((String)serverRequest.getAttachement());
//			String chaine = this.conn.recupChaineParser(d);
//
//			sendResponse(serverRequest, chaine, station);
//
//		} else 
		if ("getWsConfig_workstationID".equals(title)) {

			String idWorkStation = (String) serverRequest.getAttachement();

			DASWorkstation w = this.conn.getWorkstationWithId(idWorkStation);

			WsConfig config = this.conn.getWsConfig(w);

			sendResponse(serverRequest, config, station);

			ServerLog.log(getClass().getSimpleName(), "config : " + config);		
		} else if ("getListTemplateReport".equals(title)) {

			List<DASTemplateReport> listTemplateReport = this.conn.getListTemplateReport();
			
			sendResponse(serverRequest, (Serializable)listTemplateReport, station);
		
		} else if ("getListTemplateSupervision".equals(title)) {

			List<DASTemplateSupervision> listTemplateSupervision = this.conn.getListTemplateSupervision();
			sendResponse(serverRequest, (Serializable)listTemplateSupervision, station);
		
		} else if ("getImageTemplateSupervision".equals(title)) {

			String name = (String) serverRequest.getAttachement();
			sendResponse(serverRequest, this.conn.getImageTemplateSupervision(name), station);		

		} else if ("getListEan128".equals(title)) {

			List<DASEan128> listEan128 = this.conn.getListEan128();
			
			sendResponse(serverRequest, (Serializable)listEan128, station);	

		} else if ("getListDataModel".equals(title)) {

			List<DASDataModel> listDM = this.conn.getListDataModel();

			sendResponse(serverRequest, (Serializable)listDM, station);

//		} else if ("getBalancesAvecWs".equals(title)) {
//
//			String workstation_id = (String) serverRequest.getAttachement();
//			List<DASBalance> balances = this.conn.RecupBalancesAvecWs(workstation_id);
//
//			sendResponse(serverRequest, (Serializable)balances, station);

		} else if ("getWorkstationWithId".equals(title)) {

			String workstation_id = (String) serverRequest.getAttachement();
			DASWorkstation workstation = this.conn.getWorkstationWithId(workstation_id);

			sendResponse(serverRequest, workstation, station);

//		} else if ("getWsArticlesAvecId".equals(title)) {
//
//			String workstation_id = (String) serverRequest.getAttachement();
//			List<DASWorkstationArticle> wsArticles = this.conn.RecupWsArticlesAvecId(workstation_id);
//
//			sendResponse(serverRequest, (Serializable)wsArticles, station);

		} else if ("getWsGenericsAvecId".equals(title)) {

			String workstation_id = (String) serverRequest.getAttachement();
			List<DASGeneric> generics = this.conn.getWsGenericsWithId(workstation_id);

			sendResponse(serverRequest, (Serializable)generics, station);

		} else if ("getWsGenericsWithIdOrWithout".equals(title)) {

			String workstation_id = (String) serverRequest.getAttachement();
			List<DASGeneric> generics = this.conn.getWsGenericsWithIdOrWithout(workstation_id);

			sendResponse(serverRequest, (Serializable)generics, station);
		
		} else if ("getFctConfigsWithWsId".equals(title)) {

			String workstation_code = (String) serverRequest.getAttachement();
			List<DASFunctionalConfig> fctConfigs = this.conn.getFctConfigsWithWsId(workstation_code);

			sendResponse(serverRequest, (Serializable)fctConfigs, station);

		} else if ("getGraphConfigsWithWsId".equals(title)) {

			String workstation_id = (String) serverRequest.getAttachement();
			List<DASGraphicalConfig> graphConfigs = this.conn.getGraphConfigsWithWsId(workstation_id);

			sendResponse(serverRequest, (Serializable)graphConfigs, station);

		} else if ("getDataModelWithId".equals(title)) {
			
			String id = (String) serverRequest.getAttachement();
			DASDataModel dataModel = this.conn.getDataModelWithId(id);

			sendResponse(serverRequest, dataModel, station);
			
		} else if ("getSubjectList".equals(title)){
			messageSender.setSubject(ServerLoader.getExtName());
			
			List<String> subjectList = this.conn.getConsumConfigWithId(station);

			sendResponse(serverRequest, (Serializable) subjectList, station);
			messageSender.setSubject(ServerLoader.getClientName());
		}
		else {
			ServerLog.log(getClass().getSimpleName(), "Unhandled message : " + title);
		}

	}
	
	private void sendResponse(ServerRequest serverRequest, Serializable attachement, String station) {
		ServerRequest response = new ServerRequest(serverRequest);
		response.setAttachement(attachement);
		this.messageSender.setSubject(response.getSendUrl());
		this.messageSender.send(response, station);
	}	

	public void onMessage(Message message) {
		ServerLog.log(getClass().getSimpleName(),"Passage by onMessage");
		this.route(message);
	}

}
