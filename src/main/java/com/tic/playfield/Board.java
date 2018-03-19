package com.tic.playfield;

import java.util.ArrayList;
import java.util.List;

import com.tic.game.Position;

/**
 * Represents play field with all the player labels in the field cells.
 */
public class Board {
	
	/** Play field columns separator in string representation */
	final static char COLUMN_SEPARATOR = '|';
	
	/** Play field strings separator character in string representation */
	final static char ROW_SEPARATOR = '-';	
	
	/** System specific new line character */
	final static String NEW_LINE = System.getProperty("line.separator");

	/** Stores all the moves during the game */
	private List<List<Character>> board;

	/**
	 * Instantiates a new board.
	 *
	 * @param size the size of the play field
	 */
	public Board(int size) {
		board = new ArrayList<List<Character>>(size);
		for (int i = 0; i < size; i += 1) {
			List<Character> fieldRow = new ArrayList<Character>(size);
			for (int j = 0; j < size; j += 1) {
				fieldRow.add(Playfield.EMPTY);
			}
			board.add(fieldRow);
		}
	}
	
	/**
	 * Occupy field position by the player label
	 *
	 * @param selection the position to occupy
	 * @param label the player label
	 */
	void occupyFieldPosition(Position selection, Character label) {
		List<Character> row = board.get(selection.getRow());
		row.set(selection.getColumn(), label);
	}
	
	/**
	 * Returns player label at the specified position.
	 *
	 * @param position the position to be checked.
	 * @return the player label or whitespace if the postion is not occupied.
	 */
	char labelAtPosition(Position position) {
		return board.get(position.getRow()).get(position.getColumn());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(lineSeparator());
		for (List<Character> row : board) {
			builder.append(COLUMN_SEPARATOR);
			for (Character cell : row) {
				builder.append(cell).append(COLUMN_SEPARATOR);
			}
			builder.append(lineSeparator());
		}
		return builder.toString();
	}
	
	/**
	 * @return generates rows separator for the board string presentation.
	 */
	StringBuilder lineSeparator() {
		StringBuilder builder = new StringBuilder();
		builder.append(NEW_LINE).append(ROW_SEPARATOR);
		for (int i = 0; i < board.size(); i += 1) {
			builder.append(ROW_SEPARATOR).append(ROW_SEPARATOR);
		}
		builder.append(NEW_LINE);
		return builder;
	}
}
