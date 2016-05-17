package service.scheduler.webview.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.scheduler.core.api.CommonConstants;
import service.scheduler.core.exceptions.SystemException;
import service.scheduler.webview.service.UtilityService;

@Controller
public class ExceptionHandlingController {
	private UtilityService utilityService;
	private Logger logger = Logger.getLogger(getClass());
	
	  @ExceptionHandler(Exception.class)
	  public ModelAndView handleError(HttpServletRequest req, Exception exception) {
	    logger.error("Request: " + req.getRequestURL() + " raised " + exception);
	    
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", exception);
	    mav.addObject("url", req.getRequestURL());
	    if(exception instanceof SystemException){
	    	mav.addObject(CommonConstants.ERROR_DESCRIPTION, ((SystemException)exception).getErrorCode());
	    }else{
	    	mav.addObject(CommonConstants.ERROR_DESCRIPTION, exception.getMessage());
	    }
	    mav.setViewName("error");
	    return mav;
	  }
	  
	  @ExceptionHandler(SystemException.class)
	  public ModelAndView handleSystemError(HttpServletRequest req, SystemException exception){

		  ModelAndView mav = new ModelAndView();
		  mav.addObject("exception", exception);
		  mav.addObject("url", req.getRequestURL());
			  
		  mav.addObject(CommonConstants.ERROR_DESCRIPTION, utilityService.getSeparatedExceptionMessageChain(exception));
		  mav.addObject(CommonConstants.ERROR_MAP, exception.getProperties());
		  mav.setViewName("error");
		  return mav;
	  }

//    @ResponseStatus(HttpStatus.FORBIDDEN)
    @RequestMapping(value="/403", method=POST)
    public String handleResourceNotFoundException() {
        return "403";
    }
	  
	@Autowired
	public void setUtilityService(UtilityService utilityService) {
		this.utilityService = utilityService;
	}
	  
}
