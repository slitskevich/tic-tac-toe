package com.tic.errors;

/**
 * The Class ConfigurationException used to indicate game configuraion issues.
 */
public class ConfigurationException extends Exception {
	
	/** Error message for missing configuration file. */
	private final static String CONFIGURATION_NOT_FOUND = "Can't find configuration file at specified path";
	
	/** Error message for configuration file reading problem. */
	private final static String CONFIGURATION_READ_ERROR = "Can't read configuration file at specified path";
	
	/** Error message for missing game size */
	private final static String SIZE_MISSING_ERROR = "Size configuration is missing";
	
	/** Error message for wrong game size format */
	private final static String SIZE_FORMAT_ERROR = "Invalid size format: %s";
	
	/** Error message for invalid game size value */
	private final static String SIZE_BOUNDS_ERROR = "Invalid size of the playground. Valid values are between %d and %d: %d";

	/** Error message for missing player name configuration */
	private static final String EMPTY_PLAYER_NAME = "Missing player name";
	
	/** Error message for wrong player name */
	private static final String PLAYER_LABEL_ERROR = "Invalid player label. Should be a single character";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new configuration exception.
	 *
	 * @param message the message
	 */
	public ConfigurationException(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new configuration exception.
	 *
	 * @param message the error message
	 * @param th the root cause
	 */
	public ConfigurationException(String message, Throwable th) {
		super(message, th);
	}
	
	/**
	 * Creates exception for missing player name error
	 *
	 * @return the configuration exception
	 */
	public static ConfigurationException noPlayerName() {
		return new ConfigurationException(EMPTY_PLAYER_NAME);
	}

	/**
	 * Creates exception for invalid player name
	 *
	 * @param label player name
	 * @return the configuration exception
	 */
	public static ConfigurationException invalidPlayerName(String label) {
		return new ConfigurationException(PLAYER_LABEL_ERROR + (label != null ? ": " + label : ""));
	}
	
	/**
	 * Crates exception for not found configuration.
	 *
	 * @return the configuration exception
	 */
	public static ConfigurationException configurationNotFound() {
		return new ConfigurationException(CONFIGURATION_NOT_FOUND);
	}

	/**
	 * Creates exception when configuration can't be read
	 *
	 * @return the configuration exception
	 */
	public static ConfigurationException configurationRead() {
		return new ConfigurationException(CONFIGURATION_READ_ERROR);
	}

	/**
	 * Creates exception for missing game size error
	 *
	 * @return the configuration exception
	 */
	public static ConfigurationException sizeMissingError() {
		return new ConfigurationException(SIZE_MISSING_ERROR);
	}

	/**
	 * Creates exception for invalid size configuration
	 *
	 * @param value the value
	 * @return the configuration exception
	 */
	public static ConfigurationException wrongSizeFormat(String value) {
		return new ConfigurationException(String.format(SIZE_FORMAT_ERROR, value));
	}

	/**
	 * Creates exception for the size out of bounds configuration
	 *
	 * @param min the minimal value for the size
	 * @param max the maximal value for the size
	 * @param value configuration value
	 * @return the configuration exception
	 */
	public static ConfigurationException wrongSizeBounds(int min, int max, int value) {
		return new ConfigurationException(String.format(SIZE_BOUNDS_ERROR, min, max, value));
	}
}
