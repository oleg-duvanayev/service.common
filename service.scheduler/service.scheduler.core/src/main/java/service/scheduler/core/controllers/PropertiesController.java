package service.scheduler.core.controllers;

public interface PropertiesController {
	public String getProperty(String key);
	public String getCurrentEnvironment();
}
