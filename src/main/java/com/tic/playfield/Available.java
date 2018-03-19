package com.tic.playfield;

import java.util.ArrayList;
import java.util.List;

import com.tic.errors.PlayException;
import com.tic.game.Position;
import com.tic.util.Random;

/**
 * Data structure to store information about play field positions available for the move.
 */
public class Available {
	
	/** The list of available rows. Collection contains an item for every row. The item is the list of column indices in the row available for the move */
	private List<List<Integer>> availableRows;
	
	/**
	 * Instantiates a new instance
	 *
	 * @param size the size of the play field
	 */
	public Available(int size) {
		availableRows = new ArrayList<List<Integer>>(size);
		for (int i = 0; i < size; i += 1) {
			List<Integer> row = new ArrayList<Integer>(size);
			for (int j = 0; j < size; j += 1) {
				row.add(j);
			}
			availableRows.add(row);
		}
	}
	
	/**
	 * Pick random available position.
	 *
	 * @return randomly picked available play field position
	 * @throws PlayException the play exception
	 */
	public Position pickRandomAvailablePosition() throws PlayException {
		Position result = null;
		int rowIndex = Random.pickIndex(availableRows);
		List<Integer> availableColumns = availableRows.get(rowIndex);
		if (availableColumns.size() > 0) {
			int columnIndex = Random.pickIndex(availableColumns);
			result = new Position(rowIndex, availableColumns.get(columnIndex));
		} else {
			throw PlayException.noAvailablePositions();
		}
		return result;
	}
	
	/**
	 * Checks if is position available for the next move.
	 *
	 * @param selection the position to be tested
	 * @return true, if is position available
	 */
	boolean isPositionAvailable(Position selection) {
		List<Integer> availableColumns = availableRows.get(selection.getRow());
		return availableColumns.indexOf(selection.getColumn()) > -1;
	}
	
	/**
	 * Marks postion as unavailable. Essentially just removes corresponding index
	 *
	 * @param selection the position to be marked as unavailable
	 */
	void updateAvailablePosition(Position selection) {
		List<Integer> availableColumns = availableRows.get(selection.getRow());
		int index = availableColumns.indexOf(selection.getColumn());
		availableColumns.remove(index);
	}
}
