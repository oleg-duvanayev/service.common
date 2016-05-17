package service.scheduler.core.exceptions;

/**
 * describes general cases 
 *
 */
public enum GeneralCode implements ErrorCode {
	TECHNICAL_ERROR(100);
	
	private final int number;
	
	private GeneralCode(int number) {
		this.number = number;
	}
	
	@Override
	public int getNumber() {
		return number;
	}

}
