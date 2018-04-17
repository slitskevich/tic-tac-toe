package com.tic.playfield;

import com.tic.errors.ConfigurationException;
import com.tic.errors.PlayException;
import com.tic.game.Position;
import com.tic.player.Player;

/**
 * Represents game play field
 */
public class Playfield {
	
	final static int MINIMAL_SIZE = 3;
	final static int MAXIMAL_SIZE = 10;
	
	/** The character to represent free play field position */
	final static char EMPTY = ' ';
	
	/** The format of the message for the won game */
	final static String WON_FORMAT = "%s won!";
	
	/** The message for the game draw */
	final static String DRAW = "Draw!";
	
	/** The incomplete game status message */
	final static String INCOMPLETE = "The game is not completed";
	
	/** The data structure listing all available for moves play field positions */
	private Available available;
	
	/** The full play field with all players labels */
	private Board board;
	
	/** The data structure listing all play field paths that still can be used to win the game */
	private Winnable winnable;
	
	/** The dimension of the play field */
	int size;

	/**
	 * Instantiates a new play field.
	 *
	 * @param configuration the configuration of the play field: the dimension, the labels for all human players and finally the label of the computer player.
	 * @throws ConfigurationException if play field configuration can't be read or is invalid
	 */
	public Playfield(String configuration) throws ConfigurationException {
		if (configuration == null) {
			throw ConfigurationException.sizeMissingError();
		}
		try {
			size = Integer.parseInt(configuration);
			if (size < MINIMAL_SIZE || size > MAXIMAL_SIZE) {
				throw ConfigurationException.wrongSizeBounds(MINIMAL_SIZE, MAXIMAL_SIZE, size);
			}
			available = new Available(size);
			board = new Board(size);
			winnable = new Winnable(size);			
		} catch (NumberFormatException e) {
			throw ConfigurationException.wrongSizeFormat(configuration);
		}		
	}
	
	/**
	 * @return true, if the game is completed - won or draw; false otherwise
	 */
	public boolean isCompleted() {
		return winnable.getWinner() != null || !winnable.haveWinnablePaths();
	}
	
	/**
	 * @return the result status message
	 */
	public String getResultMessage() {
		if (winnable.getWinner() != null) {
			return String.format(WON_FORMAT, winnable.getWinner().latestPlayer());
		} else if (!winnable.haveWinnablePaths()) {
			return DRAW;
		}
		return INCOMPLETE;
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
			winnable.updateWinnablePaths(nextMove, player.label);
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
	
}
