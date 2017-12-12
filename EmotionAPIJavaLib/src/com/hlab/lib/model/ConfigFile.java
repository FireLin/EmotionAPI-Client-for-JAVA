package com.hlab.lib.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFile {
	
	private Properties properties;
	
	public ConfigFile(){
		connectToPropertiesFile();
	}
	
	public ConfigFile(int teamid){
		connectToPropertiesFile();
	}
	
	private void connectToPropertiesFile(){
		properties = new Properties();
		try {
			properties.load(new FileInputStream("config.properties"));
		} catch (IOException e) {
			System.out
					.println("ERROR (class preferences): can't open "
							+ "properties file 'config.properties'. Please validate "
							+ "that the file exists and the user rights for reading "
							+ "are given. Error message: " + e.toString());
			System.exit(0);
		}
	}
	
	
	public String getEmotionAPIKey(){
		return properties.getProperty("emotion.apikey");
	}
	
	public String getFileVisionFolderPath(){
		return properties.getProperty("files.visionimagepath");
	}
	
}
