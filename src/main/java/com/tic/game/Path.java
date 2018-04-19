package com.tic.game;

import com.tic.playfield.Playfield;

/**
 * The class represents a sequence of cells on the play field which once occupied by a single player would represent a winning path.
 */
public class Path {
	
	/** The flag indicating some player could still win the game with this path. */
	private boolean winnable = true;
	
	/** The latest player label put into any cell of the path; original value: Playfield.EMPTY */
	private char latest = Playfield.EMPTY;
	
	/** The total count of unoccupied cells in the path */
	private int emptyCount;
	
	/**
	 * Instantiates a new path.
	 *
	 * @param size the number of positions of the path.
	 */
	public Path(int size) {
		this.emptyCount = size;
	}

	/**
	 * Updates path status after the player occupies a position in the path
	 *
	 * @param label the name of the player
	 */
	void putLabel(char label) {
		if (this.latest != Playfield.EMPTY && this.latest != label) {
			winnable = false;
		}
		this.latest = label;
		this.emptyCount -= 1;
	}
	
	/**
	 * @return true, if all path positions are occupied by a single player; false otherwise
	 */
	boolean isWinner() {
		return winnable && emptyCount == 0;
	}
	
	/**
	 * @return true, if at least some player could still occupy all the path position and win the game; false otherwise
	 */
	boolean isWinnable() {
		return winnable;
	}
	
	/**
	 * @return the label of the player who occupied some path position last.
	 */
	char latestPlayer() {
		return latest;
	}
}