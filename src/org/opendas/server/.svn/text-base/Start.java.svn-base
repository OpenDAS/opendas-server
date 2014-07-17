package org.opendas.server;

/**
 * Main class, starting application
 */
public class Start {
	
	public static void main(String[] args) {
		try{
		
			if (args.length>0) {
				ServerLoader.getInstance(args[0]);
				ServerLog.log("Start","OpenDAS Server starts with configuration file: " + args[0]);
			}
			else { 
				ServerLoader.getInstance("./config/das_server.conf");
				ServerLog.log("Start","OpenDAS Server starts with default configuration file: ./config/das_server.conf");
			}
		
		}catch(Exception ex){
			ServerLog.log("Start","Throw exception");
			//tr.printStackTrace();
		}
		//ServerLog.getInstance();
	}

}
