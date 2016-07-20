package ipn;

public class NoSignatureException extends RuntimeException {

	public NoSignatureException() {
	}

	public NoSignatureException(String arg0) {
		super(arg0);
	}

	public NoSignatureException(Throwable arg0) {
		super(arg0);
	}

	public NoSignatureException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NoSignatureException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
