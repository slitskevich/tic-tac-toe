package com.tic.errors;

/**
 * The Class TicSystemException represents runtime system errors.
 */
public class TicSystemException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	/** General game initialization failure message */
	private static String INIT_ERROR = "Failed to initialize the game";
	
	/**
	 * Instantiates a new configuration exception.
	 *
	 * @param message the message
	 */
	public TicSystemException(String message) {
		super(message);
	}

	/**
	 * Generates general game initialization error
	 * @return TicSystemException for the error
	 */
	public static TicSystemException initializationException() {
		return new TicSystemException(INIT_ERROR);
	}
}
