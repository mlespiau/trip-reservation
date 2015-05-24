package framework;

public class RequiredParameterException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RequiredParameterException(String message) {
		super(message);
	}

	public static RequiredParameterException createFromParameterName(String parameter) {
		return new RequiredParameterException("Error: " + parameter + " parameter is required");
	}
}
