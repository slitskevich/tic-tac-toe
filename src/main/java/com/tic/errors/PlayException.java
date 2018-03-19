package com.tic.errors;

/**
 * The Class PlayException indicates errors occurring during the game
 */
public class PlayException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2L;
	
	final static String UNAVAILABLE_POSITION_ERROR = "Selected position is not available";
	final static String INVALID_POSITION_ERROR = "Selected position is not valid";
	final static String NO_AVAILABLE_POSITIONS_ERROR = "Playfield has no available positions";
	final static String FAILED_MOVE_ERROR = "Failed to make a move";
	final static String POSITION_FORMAT_ERROR = "Wrong format of position description";
	
	/**
	 * Instantiates a new play exception.
	 *
	 * @param message the error message
	 */
	public PlayException(String message) {
		super(message);
	}
	
	/**
	 * Creates exception for wrong position format error
	 *
	 * @param line the position configuration line
	 * @return the play exception
	 */
	public static PlayException wrongPositionFormat(String line) {
		return new PlayException(POSITION_FORMAT_ERROR + ": " + line);
	}

	/**
	 * Creates exception indicating the position is already taken.
	 *
	 * @return the play exception
	 */
	public static PlayException unavaialblePosition() {
		return new PlayException(UNAVAILABLE_POSITION_ERROR);
	}

	/**
	 * Creates exception indicating the position has invalid coordinates
	 *
	 * @return the play exception
	 */
	public static PlayException invalidPosition() {
		return new PlayException(INVALID_POSITION_ERROR);
	}

	/**
	 * Creates exception indicating there is no available positions left in the game.
	 *
	 * @return the play exception
	 */
	public static PlayException noAvailablePositions() {
		return new PlayException(NO_AVAILABLE_POSITIONS_ERROR);
	}
}
