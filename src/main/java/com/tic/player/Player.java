package com.tic.player;

import com.tic.errors.ConfigurationException;
import com.tic.errors.PlayException;
import com.tic.game.Position;
import com.tic.playfield.Playfield;

/**
 * Abstract class for the game player.
 * @see HumanPlayer, ComputerPlayer
 */
public abstract class Player {
	
	/** The player label used to mark his moves on the play field. */
	public char label;
	
	/** The play field of the game. */
	protected Playfield playfield;
	
	/**
	 * Instantiates a new player.
	 *
	 * @param label the label of the player
	 * @param playfield the game play field
	 * @throws ConfigurationException the configuration exception
	 */
	public Player(String label, Playfield playfield) throws ConfigurationException {
		if (label == null || label.length() == 0) {
			throw ConfigurationException.noPlayerName();
		}
		if (label.length() > 1) {
			throw ConfigurationException.invalidPlayerName(label);
		}
		this.label = label.charAt(0);
		this.playfield = playfield;
	}
	
	/**
	 * Chooses new position for the next move
	 *
	 * @return selected position for the next move
	 * @throws PlayException the play exception
	 */
	protected abstract Position choosePosition() throws PlayException;
	
	/**
	 * Makes a new move in the game.
	 *
	 * @throws PlayException the play exception
	 */
	public void move() throws PlayException {
		playfield.takePosition(this, choosePosition());
	}
}
