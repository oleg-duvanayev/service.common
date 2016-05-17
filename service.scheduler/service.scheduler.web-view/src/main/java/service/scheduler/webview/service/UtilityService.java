package service.scheduler.webview.service;

import java.io.InputStream;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;

import org.springframework.stereotype.Component;

import service.scheduler.core.exceptions.ErrorCode;
import service.scheduler.core.exceptions.SystemException;


/**
 * http://www.mkyong.com/spring/spring-how-to-access-messagesource-in-bean-messagesourceaware/
 *
 */
@Component
public class UtilityService {

	/**
	 * separate the token with "\\n\\r"
	 * @param exception
	 * @return
	 */
	public String getSeparatedExceptionMessageChain(Throwable exception){
		String[] list = determinationExceptionMessagePipe(exception);
		StringBuilder sb = new StringBuilder();
		for (int ind = 0;ind < list.length ;ind++) {
			String msg = list[ind];
			sb.append(msg);
			if(ind+1 < list.length){
				sb.append("\n\r");
			}
		}

		return sb.toString();
	}
	
	public String[] determinationExceptionMessagePipe(Throwable exception) {
		Collection<String> datas = getExceptionMessageChain(exception);
		List<String> returnValue = new ArrayList<String>();
		for (String string : datas) {
			if(string != null){
				returnValue.add(string);
			}
		}
		return returnValue.toArray(new String[]{});
	}
	
	private Collection<String> getExceptionMessageChain(Throwable throwable) {
		Set<String> result = new HashSet<String>();
		while (throwable != null) {
			if(throwable instanceof SystemException){
				SystemException se = (SystemException) throwable;
				String hrm = getUserText(se.getErrorCode());
				result.add(hrm);
			}else{
				if(throwable instanceof UndeclaredThrowableException){
					UndeclaredThrowableException ute = (UndeclaredThrowableException) throwable;
					SystemException ex = (SystemException) ute.getUndeclaredThrowable();
					String hrm = getUserText(ex.getErrorCode());
					result.add(hrm);
				}else{
					result.add(throwable.getMessage());
				}
			}
			
			throwable = throwable.getCause();
		}
		return Collections.unmodifiableCollection(result);
	}
	
	/**
	 * TODO FIXME. you must here determine the correct properties file !
	 * @param errorCode
	 * @return
	 */
    public String getUserText(ErrorCode errorCode) {
        if (errorCode == null) {
            return null;
        }
        String message=null;
        String key = errorCode.getClass().getSimpleName() + "__" + errorCode;
        // TODO FIXME: to declare the source name "message" some-where generally
        ResourceBundle bundle = ResourceBundle.getBundle("messages");
        message = bundle.getString(key);
        return message;
    }
    
	public String readSource(String source){
		InputStream expectedResourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(source);
		String expected = new Scanner(expectedResourceStream,"UTF-8").useDelimiter("\\A").next();
		return expected;
	}
}
