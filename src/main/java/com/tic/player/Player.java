package com.tic.player;

import java.io.PrintStream;

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
	
	/** Messages output stream for the player */
	protected PrintStream output;
	
	/**
	 * Instantiates a new player.
	 *
	 * @param label the label of the player
	 * @throws ConfigurationException the configuration exception
	 */
	public Player(String label) throws ConfigurationException {
		if (label == null || label.length() == 0) {
			throw ConfigurationException.noPlayerName();
		}
		if (label.length() > 1) {
			throw ConfigurationException.invalidPlayerName(label);
		}
		this.label = label.charAt(0);
	}
	
	/**
	 * Sets the playfield reference
	 * @param playfield the game playfield
	 */
	public void setPlayfield(Playfield playfield) {
		this.playfield = playfield;
	}
	
	/**
	 * Sets the messages output stream reference
	 * @param output the stream to output messages
	 */
	public void setOutput(PrintStream output) {
		this.output = output;
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
