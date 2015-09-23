package br.interactive.ecm.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe utilit�ria para leitura de properties.
 * @author robson.ramos
 */
public final class PropertiesUtil {

	/** Property for project.properties. */
	private Properties projectProperties;
	
	/** Nome do property */
	private String propertyFile;

	
	public PropertiesUtil(String propertyFile) {
		this.propertyFile = propertyFile; 
		readProjectProperties();
	}

	/**
	 * Read project.propeties file.
	 */
	private void readProjectProperties() {
		projectProperties = new Properties();
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream is = classLoader.getResourceAsStream( propertyFile );
			projectProperties.load(is);
		} catch (IOException e) {
			throw new RuntimeException("Error read properties", e);
		}
	}

	/**
	 * Return valur from given property in project.property file.
	 * @param key
	 * @return String
	 */
	public String getProperty(String key) {
		return projectProperties.getProperty(key);
	}
}
