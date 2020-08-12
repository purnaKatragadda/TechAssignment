package com.evaluation.choicesuper.config;

import java.io.FileInputStream;
import java.util.Properties;



public class AppProperties {

	public static Properties appProps;
	 
	static {
		
		try {
		
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		//System.out.println(rootPath);
		String appConfigPath = rootPath + "application.properties";
		 
		appProps = new Properties();
		appProps.load(new FileInputStream(appConfigPath));
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Cannot load config");
		}
	}

   
}