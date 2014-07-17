package org.opendas.ext;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import org.opendas.server.ServerLog;

public class PropertiesAccess {

	private static Properties prop;
	
	public PropertiesAccess(String fileName) {

		if (prop == null) {

			prop = new Properties();

			try {

				prop.load(new FileInputStream(fileName));

			} catch (IOException e) {
				try {

					prop.load(new BufferedReader(new InputStreamReader(new URL(fileName).openStream())));

				} catch (IOException e2) {
					ServerLog.logDebug(this.getClass().getSimpleName(),"Reading error of the property file " + fileName);
					e2.printStackTrace();
				}
			}
		}
	}

	/**
	 * read a property value
	 * 
	 * @param title
	 * @return null if not found
	 */
	public String get(String title) {
		return prop.getProperty(title);
	}
}
