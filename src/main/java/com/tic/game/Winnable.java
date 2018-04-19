package com.tic.game;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class represents the list of possible paths the player could fill in to win, e.g. the sequence of positions player could occupy.
 * When the game starts those are all columns, all rows and two diagonals - forward and backward. As soon as multiple players occupy 
 * positions in the same path it is considered that it can't be won by anyone.
 */
public class Winnable {
	
	/** The list of rows. */
	private List<Path> winnableRows;
	
	/** The list of columns. */
	private List<Path> winnableColumns;
	
	/** The forward diagonal. */
	Path forwardDiagonal;
	
	/** The backward diagonal. */
	Path backwardDiagonal;
	
	/** The winner path - the path where all the positions are occupied by a single player. */
	private Path winner;
	
	/** The dimension of the play field. */
	private int size;
	
	/**
	 * Instantiates a new instance.
	 *
	 * @param size the dimension of the play field.
	 */
	public Winnable(int size) {
		this.size = size;
		
		forwardDiagonal = new Path(size);
		backwardDiagonal = new Path(size);
		winnableRows = new ArrayList<Path>();
		winnableColumns = new ArrayList<Path>();
		for (int i = 0; i < size; i += 1) {
			winnableRows.add(new Path(size));
			winnableColumns.add(new Path(size));
		}
	}
	
	/**
	 * Update the state of all paths collections.
	 *
	 * @param selection the position to be marked as occupied
	 * @param label the name of the player
	 */
	void updateWinnablePaths(Position selection, Character label) {
		updatePath(winnableRows.get(selection.getRow()), label);
		updatePath(winnableColumns.get(selection.getColumn()), label);
		if (selection.getRow() == selection.getColumn()) {
			updatePath(forwardDiagonal, label);
		}
		if (selection.getRow() == size - selection.getColumn() - 1) {
			updatePath(backwardDiagonal, label);
		}
	}
	
	/**
	 * Updates specific path as containing a position occupied by a player.
	 *
	 * @param path the path to update
	 * @param label the player name
	 */
	private void updatePath(Path path, Character label) {
		path.putLabel(label);
		if (path.isWinner()) {
			this.winner = path;
		}
	}
	
	/**
	 * @return true, if the play field still contains any paths that can be won; false otherwise
	 */
	boolean haveWinnablePaths() {
		return forwardDiagonal.isWinnable() ||
			   backwardDiagonal.isWinnable() ||
			   haveWinnableItem(winnableRows) ||
			   haveWinnableItem(winnableColumns);
	}
	
	/**
	 * Checks whether specific play field path collection contains path that still can be won.
	 *
	 * @param collection the collection to be tested
	 * @return true, if collection contains the path that can be won; false otherwise
	 */
	private boolean haveWinnableItem(List<Path> collection) {
		for (Path next : collection) {
			if (next.isWinnable()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return the play field path where all the positions are occupied by a single player.
	 */
	public Path getWinner() {
		return winner;
	}
}