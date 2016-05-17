package service.scheduler.core.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * https://northconcepts.com/blog/2013/01/18/6-tips-to-improve-your-exception-handling/
 *
 */
@SuppressWarnings("serial")
public class SystemException extends RuntimeException implements ErrorCode {
	private ErrorCode errorCode;
	private Map<String, Object> properties = new HashMap<String, Object>();
	
	public SystemException(ErrorCode errorCode) {
		this(null,null,errorCode);
	}
	
	public SystemException(String message, Throwable exception, ErrorCode errorCode) {
		super(message,exception);
		this.errorCode = errorCode;
	}

	public <T> T get(String key){
		return (T) properties.get(key);
	}
	
	public SystemException set(String key, Object value) {
		properties.put(key, value);
		return this;
	}
	
	@Override
	public int getNumber() {
		return errorCode.getNumber();
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}
	
	public static SystemException wrap(Throwable exception, ErrorCode errorCode) {
		  if (exception instanceof SystemException) {
		    SystemException se = (SystemException)exception;
		    if (errorCode != null && errorCode != se.getErrorCode()) {
		      return new SystemException(exception.getMessage(), exception, errorCode);
		    }
		    return se;
		  } else {
		    return new SystemException(exception.getMessage(), exception, errorCode);
		  }
		}

	public static SystemException wrap(Throwable exception) {
	  return wrap(exception, null);
	}

}
