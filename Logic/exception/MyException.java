package exception;



public class MyException extends Exception {
	public static final  int EMAIL_ERROR = 400;
	public static final  int CARITAS_ERROR = 200;
	public static  final  int NEGOZIO_ERROR = 300;
	public static final  int VOLONTARIO_ERROR = 100;
	public static final  int CAMPI_VUOTI = 103; 
	public static final  int ORARIO = 105;
	public static final  int UTENTE_NON_REGISTRATO = 106;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int errorCode;

	
	
	
	public MyException (String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public MyException (Throwable cause, int errorCode) {
		super(cause);
		this.errorCode = errorCode;

	}

	public MyException (String message, Throwable cause, int errorCode) {
		super(" +++ " + message + " +++ ", cause);
		this.errorCode = errorCode;

	}


	public int getErrorNumber() {
		return this.errorCode;
	}
	
	@Override
	public String getMessage() {
		return super.getMessage() + " Code = " + errorCode;
	}
	
}
