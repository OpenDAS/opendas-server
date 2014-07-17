package org.opendas.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class managing the logs
 * @author mlaroche
 */
public class ServerLog {

	private static ServerLog instance = new ServerLog();
	private static PrintStream logPSOut;
	private static PrintStream logPSErr;
	
	private ServerLog() {
		
		// Redirection des logs vers des fichiers
//		logPSOut = System.out;
//		logPSErr = System.err; 
		try {
			String logout = null;
			String log_file = null; 
			if (ServerLoader.getLog_path() != null)
			{
				log_file = ServerLoader.getLog_path();
			}
			else
			{
				log_file = "./logs/";
			}
			DateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			logout = "./logs/DAS_" + df.format(new Date()) + ".log";
			File rep = new File(log_file);

			if (!rep.exists()) {
			    log(this.getClass().getSimpleName(),"Directory not found");
			   log(this.getClass().getSimpleName(),"Create directory logs ");
			    new File(log_file).mkdir();
			}

			ServerLog.log(this.getClass().getSimpleName(),"Logs will be record in the file " + logout);
			
			logPSOut = new PrintStream(new FileOutputStream(logout));
			logPSErr = new PrintStream(new FileOutputStream(logout));
			
//			System.setOut(logPSOut);
//			System.setErr(logPSErr);
			
		} catch (FileNotFoundException e) {
			logErr(this.getClass().getSimpleName(),"Error during the log file definition");
			e.printStackTrace();
		}
	}
	
	public static ServerLog getInstance() {
		return instance;
	}
	
	public static void log(String classe, String message) {
		if(logPSOut != null) 
			logPSOut.println(classe + ": " + message);
		System.out.println(classe + ": " + message);
	}

	public static void logDebug(String classe, String message) {
		if (ServerLoader.debugMode()) {
			if(logPSOut != null) 
				logPSOut.println(classe + ": " + message);
			System.out.println(classe + ": " + message);
		}
	}
	
	public static void logErr(String classe, String message) {
		if(logPSOut != null) 
			logPSErr.println("[ERREUR] " + classe + ": " + message);
		System.err.println("[ERREUR] " + classe + ": " + message);
	}
	
}
