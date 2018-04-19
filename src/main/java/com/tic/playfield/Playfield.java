package com.tic.playfield;

import com.tic.errors.ConfigurationException;
import com.tic.errors.PlayException;
import com.tic.game.Position;
import com.tic.player.Player;

/**
 * Represents game play field
 */
public class Playfield {
	
	/** The character to represent free play field position */
	public final static char EMPTY = ' ';
	
	/** The data structure listing all available for moves play field positions */
	private Available available;
	
	/** The full play field with all players labels */
	private Board board;
	
	/** The dimension of the play field */
	int size;

	/**
	 * Instantiates a new play field.
	 *
	 * @param size the dimension of the play field board.
	 * @throws ConfigurationException if play field configuration can't be read or is invalid
	 */
	public Playfield(int size) throws ConfigurationException {
		this.size = size;
		available = new Available(size);
		board = new Board(size);
	}
	
	/**
	 * @return the random position available for the move
	 * @throws PlayException the play exception if there is no available positions
	 */
	public Position pickRandomPosition() throws PlayException {
		return available.pickRandomAvailablePosition();
	}

	/**
	 * Occupies the play field position with the label of the player
	 *
	 * @param player the player to occupy the position
	 * @param nextMove the position to occupy
	 * @throws PlayException if the position is not valid or not available
	 */
	public void takePosition(Player player, Position nextMove) throws PlayException {
		if (!isPositionValid(nextMove)) {
			throw PlayException.invalidPosition();
		} else if (!available.isPositionAvailable(nextMove)) {
			throw PlayException.unavaialblePosition();
		} else {
			available.updateAvailablePosition(nextMove);
			board.occupyFieldPosition(nextMove, player.label);
		}
	}

	/**
	 * Checks if is position valid.
	 *
	 * @param selection the position to be checked
	 * @return true, if the position is valid; false otherwise
	 */
	public boolean isPositionValid(Position selection) {
		return selection.getRow() < size && selection.getRow() >= 0 && selection.getColumn() < size && selection.getColumn() >= 0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return board.toString();
	}
	
	/**
	 * @return the play field board
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * @return available for the move cells structure
	 */
	public Available getAvailable() {
		return available;
	}
	
}
