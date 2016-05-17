package service.scheduler.core.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller
public class PropertiesControllerImpl implements PropertiesController{
	public static final String DEFAULT_ENV="development"; 

	private Logger logger = Logger.getLogger(getClass());
	private Properties properties = new Properties();
	private String currentEnvironment;
	
	public PropertiesControllerImpl() {
		;
	}
	
	@PostConstruct
	private void init(){
		String resourceId =null;
		InputStream inStream = null;
		try {
			// define of current environment
			currentEnvironment = System.getProperties().getProperty("common.service.env", DEFAULT_ENV);
			
			// read common properties accordance to environment
			inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("env-"+currentEnvironment+".properties");
			properties.load(inStream);
			logger.debug("initialized");
		
			// read db properties accordance to environment
			resourceId = "env-"+getCurrentEnvironment()+"-db.properties";
			inStream =Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceId);
			properties.load(inStream);
			logger.debug("properties file ["+resourceId+"] loaded ok");
		
		} catch (IOException e) {
			logger.error("can not load the ["+resourceId+"] file",e);
		}
	}
	
	@Override
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	@Override
	public String getCurrentEnvironment() {
		return currentEnvironment;
	}

}
