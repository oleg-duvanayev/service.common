package service.scheduler.webview.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.scheduler.core.controllers.PropertiesController;
import service.scheduler.core.exceptions.GeneralCode;
import service.scheduler.core.exceptions.SystemException;

@Controller
public class FrontController {
	private PropertiesController propertiesController;
	private ExceptionHandlingController exceptionHandler;
	
	@RequestMapping(value={"/","/home"}, method=GET)
	public String home(Model model){
		model.addAttribute("currentEnvironment", propertiesController.getCurrentEnvironment());
		return "home";
	}

	@RequestMapping(value={"/error"}, method=GET)
	public String checkErrorPage(Model model){
		throw new SystemException(GeneralCode.TECHNICAL_ERROR);
	}
	
	
	@ExceptionHandler(SystemException.class)
	public ModelAndView handleSystemError(HttpServletRequest req, SystemException exception){
	  return exceptionHandler.handleSystemError(req, exception);	  
	}
	  
	@Autowired
	public void setExceptionHandler(ExceptionHandlingController exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	@Autowired
	public void setPropertiesController(PropertiesController propertiesController) {
		this.propertiesController = propertiesController;
	}
}
