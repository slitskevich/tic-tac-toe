package com.tic.game;

import com.tic.errors.PlayException;

/**
 * Represents position on the play field
 */
public class Position {
	
	/** Position configuration row and column separator */
	public static final String SEPARATOR = ",";
	
	/** String format for the position instances */
	private static final String OUTPUT_FORMAT = "%d" + SEPARATOR + "%d";

	/** The position row. */
	int row;
	
	/** The position column. */
	int column;
	
	/**
	 * Takes user input in the format "row,column" and creates position instace for it.
	 *
	 * @param line the user input line
	 * @return created position instance
	 * @throws PlayException the play exception
	 */
	public static Position positionWithUserInput(String line) throws PlayException {
		Position result = null;
		if (line == null) {
			throw PlayException.invalidPosition();
		}
		String[] components = line.split(SEPARATOR);
		if (components.length == 2) {
			try {
				result = new Position();
				result.row = Integer.parseInt(components[0].trim()) - 1;
				result.column = Integer.parseInt(components[1].trim()) - 1;
			} catch (NumberFormatException ex) {
				throw PlayException.wrongPositionFormat(line);
			}
		} else {
			throw PlayException.wrongPositionFormat(line);
		}
		return result;
	}
	
	/**
	 * Instantiates a new position without row and column.
	 */
	private Position() {
		
	}
	
	/**
	 * Instantiates a new position with provided row and column values
	 *
	 * @param row position row
	 * @param column position column
	 */
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return String.format(OUTPUT_FORMAT, row + 1, column + 1);
	}
	
	/**
	 * Gets the row.
	 *
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Gets the column.
	 *
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

}
