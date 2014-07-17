package org.opendas.server;

import java.util.List;

import org.opendas.ext.DASConnexion;
import org.opendas.ext.DASIDataAccess;
import org.opendas.ext.PropertiesAccess;
import org.opendas.jms.MessageReceiver;
import org.opendas.jms.MessageSender;
import org.opendas.jms.ctrl.MessageController;
/**
 * Class permiting to load configuration at start
 */
public class ServerLoader {
	
	PropertiesAccess pa;
	
	private static ServerLoader instance = new ServerLoader();
	private static String confFile;
	private static boolean debugMode;
	private static String broker_url;
	private static String serverName;
	private static String clientName;
	private static String extName;
	private static String user;
	private static String password;
	private static String log_path;
	
	public static ServerLoader getInstance(String file){
		confFile = file;
		instance.build();
		return instance;
	}
	
	public static String getClientName() {
		return clientName;
	}
	
	public static String getExtName() {
		return extName;
	}
	
	public static boolean debugMode() {
		return debugMode;
	}


	/**
	 * 
	 */
	private ServerLoader(){
	}
	
	private void build() {
		pa = new PropertiesAccess(confFile);
		
		debugMode = "1".equals(pa.get("debugMode"));
		
		broker_url = pa.get("JMSUrl");
		serverName = pa.get("JMSPoolNameServer");
		clientName = pa.get("JMSPoolNameClient");
		extName = pa.get("JMSPoolNameExt");
		user = pa.get("JMSUser");
		password = pa.get("JMSPassword");
		log_path = pa.get("log_path");

		try {
			loadConnections();
		}
		catch (Exception e) {
			ServerLog.log(this.getClass().getSimpleName(),"catch exception");
			e.printStackTrace();
		}
	}
	
	private void loadConnections() throws Exception{
		
		DASIDataAccess con = DASConnexion.getInstance();
		List<String> sujets = con.getConsumConfigWithId("DAS_Server");
		
		MessageReceiver mr = MessageReceiver.getInstance(sujets);
		mr.setUser(user);
		mr.setPassword(password);
		mr.setUrl(broker_url);
		mr.setSubject(serverName);
		mr.setListener(new MessageController());
		mr.run();
		
		MessageSender ms = MessageSender.getInstance();
		ms.setUser(user);
		ms.setPassword(password);
		ms.setUrl(broker_url);
		ms.setSubject(clientName);
		ms.setPersistent(true);
		ms.run();		
		
	}

	public static void setLog_path(String log_path) {
		ServerLoader.log_path = log_path;
	}

	public static String getLog_path() {
		return log_path;
	}

}
